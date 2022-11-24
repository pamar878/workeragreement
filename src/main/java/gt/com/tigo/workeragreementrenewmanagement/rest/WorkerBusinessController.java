package gt.com.tigo.workeragreementrenewmanagement.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import gt.com.tigo.workeragreementrenewmanagement.commons.logger.LoggerService;
import gt.com.tigo.workeragreementrenewmanagement.commons.util.TypeLog;
import gt.com.tigo.workeragreementrenewmanagement.config.TblInitDBConfiguration;
import gt.com.tigo.workeragreementrenewmanagement.dtos.OauthOnbaseResponse;
import gt.com.tigo.workeragreementrenewmanagement.dtos.QueueResponseMessageDTO;
import gt.com.tigo.workeragreementrenewmanagement.dtos.ResponseCreateContract;
import gt.com.tigo.workeragreementrenewmanagement.dtos.ResponseDTO;
import gt.com.tigo.workeragreementrenewmanagement.dtos.ResponseQueryContract;
import gt.com.tigo.workeragreementrenewmanagement.models.dto.ContractDTO;
import gt.com.tigo.workeragreementrenewmanagement.models.dto.LogDTO;
import gt.com.tigo.workeragreementrenewmanagement.models.dto.PayloadDTO;
import gt.com.tigo.workeragreementrenewmanagement.models.dto.PlatformDTO;
import gt.com.tigo.workeragreementrenewmanagement.models.dto.payload.AgreementItem;
import gt.com.tigo.workeragreementrenewmanagement.services.ContractService;
import gt.com.tigo.workeragreementrenewmanagement.services.PlatformService;

@Controller
public class WorkerBusinessController {

	//private final Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	private LoggerService loggerService;
	
	@Autowired
	private TblInitDBConfiguration tblInitConfig;

	@Autowired
	private ContractService contractService;

	@Autowired
	private PlatformService platformService;

	@Autowired
	private OnbaseServiceController onbaseServiceController;
	
	/**
	 * logica de negocio para crear contrato
	 * @param payload {@link QueueResponseMessageDTO}
	 * 
	 * @return {@link ResponseDTO}
	 * 
	 * */
	public ResponseDTO createContract (PayloadDTO payload) {
		
		Long startTime = new Date().getTime();
		LogDTO logDTO = LogDTO.builder().apiKey("createContractApi?"+payload.getData().getAttributes().getDocumentId())
				.time(startTime).build();
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		StringBuilder msg = new StringBuilder();
		
		try {
			Optional<AgreementItem> horusIdObject = payload.getData().getAgreementItem().stream().filter(p -> p.getName().equals("CC_horus_id")).findAny();
			if(horusIdObject.isPresent()) {
				ContractDTO contract = contractService.getContractByHorusId(horusIdObject.get().getValue());
				if(contract != null) {
					responseDTO.setDescription("ContractId ::" + contract.getIdContract() +  " HorusId ::" + contract.getIdHorus() + ":: ya existe");
					msg.append("ContractId ::" + contract.getIdContract() +  " HorusId ::" + contract.getIdHorus() + ":: ya existe").append(";");
				}else {
					PlatformDTO platformDTO = platformService.getPlatformBySource(payload.getData().getSource());
					if(platformDTO != null && platformDTO.getIdStatus() == 3) {
						OauthOnbaseResponse responseToken = onbaseServiceController.getToken();
						
						ResponseCreateContract createContract = onbaseServiceController.createContract(payload, responseToken.getAccess_token());
						
						if(createContract != null) {
							ContractDTO newContract = new ContractDTO();
							newContract.setIdContract(Integer.parseInt(createContract.getData().getAttributes().getDocumentId()));
							newContract.setIdPlatform(platformDTO);
							newContract.setIdStatus(0);
							newContract.setIdHorus(horusIdObject.get().getValue());
							newContract.setLastQueryDatetime(Calendar.getInstance().getTime());
							newContract.setRequestDatetime(Calendar.getInstance().getTime());
							newContract.setStatusDatetime(Calendar.getInstance().getTime());
							
							boolean save = contractService.crearContrato(newContract);
							
							responseDTO.setDescription("Contrato Creado exitosamente");
							responseDTO.setContractId(createContract.getData().getAttributes().getDocumentId());
							
							msg.append("Contrato creado en Base de Datos::" + newContract.getIdContract() + "::" + save).append(";");
							
						}else {
							responseDTO.setDescription("Error creando el contrato Onbase para horusId ::" + horusIdObject.get().getValue());
							msg.append("Error creando el contrato Onbase para horusId ::" + horusIdObject.get().getValue()).append(";");
						}
						
					} else {
						responseDTO.setDescription("No se encontro Plataforma para SOURCE ::" + payload.getData().getSource() + " o la plataforma no se encuentra en estado activo");
						msg.append("No se encontro Plataforma para SOURCE ::" + payload.getData().getSource() + " o la plataforma no se encuentra en estado activo").append(";");
					}
					
				}
			}
		} catch (Exception e) {
			loggerError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), logDTO, responseDTO);
			return responseDTO;
		} finally {
			Long endTime = new Date().getTime();
			Long time = endTime-startTime;
			
			logDTO.setLevel(TypeLog.ORQ.name());
			logDTO.setResponseCode("" + 200);
			logDTO.setMsg("" + msg.toString());
			logDTO.setResponseTime("" + time);
			loggerService.log(logDTO);
		}
		
		return responseDTO;
		
	}
	
	/**
	 * logica de negocio para consultar contrato
	 * @param ccHorus {@link String}
	 * 
	 * @return {@link ResponseDTO}
	 * 
	 * */
	public ResponseDTO queryContract (String ccHorus) {
		
		Long startTime = new Date().getTime();
		LogDTO logDTO = LogDTO.builder().apiKey("queryContractApi?"+ccHorus)
				.time(startTime).build();
		
		ResponseDTO responseDTO = new ResponseDTO();
		StringBuilder msg = new StringBuilder();
		
		try {
			String horusIdObject = ccHorus;
			if(horusIdObject != null) {
				
				OauthOnbaseResponse responseToken = onbaseServiceController.getToken();
				ResponseQueryContract queryContract = onbaseServiceController.queryContract(horusIdObject, responseToken.getAccess_token());
				
				if(queryContract != null) {
					Optional<AgreementItem> aggTemp = queryContract.getAgreementItem().stream().filter(p -> p.getName().equals(this.tblInitConfig.getTblParametersMap().get("QUERY_FIELD"))).findAny();
					if(aggTemp.isPresent()) {
						String status = aggTemp.get().getValue();
						
						if(this.tblInitConfig.getTblParametersMap().get("QUERY_STATUS").equals(status)) {
							msg.append("[ documentId message::" + ccHorus + "]" + " ::Contrato Aceptado").append(";");
							ContractDTO contract = contractService.getContractByHorusId(horusIdObject);
							if(contract != null) {
								
								PlatformDTO plataforma = platformService.getPlatformById(contract.getIdPlatform().getIdPlatform());
								
								contract.setIdStatus(1);
								contract.setLastQueryDatetime(Calendar.getInstance().getTime());
								contract.setStatusDatetime(Calendar.getInstance().getTime());
								boolean update = contractService.crearContrato(contract);
								msg.append("Contrato actualizado en Base de Datos::" + contract.getIdContract() + "::" + update).append(";");
								
								onbaseServiceController.notify(String.valueOf(contract.getIdContract()), responseToken.getAccess_token(),this.tblInitConfig.getTblParametersMap().get("ACCEPTED_RESPONSE"), plataforma.getUrl());
								
								responseDTO.setDescription("Contrato Aceptado");
								responseDTO.setContractId(contract.getIdContract().toString());
								responseDTO.setContractStatus(status);
								
							}else {
								responseDTO.setDescription("No se encontro contrato en Base de Datos");
								msg.append("No se encontro contrato en Base de Datos").append(";");
							}
						}else {
							
							ContractDTO contract = contractService.getContractByHorusId(horusIdObject);
							if(contract != null) {
								
								long diff = contract.getLastQueryDatetime().getTime() - contract.getRequestDatetime().getTime();
								msg.append("REQUEST_DATE_TIME::" + contract.getRequestDatetime().getTime() + ":: LAST_QUERY_DATETIME::" + contract.getLastQueryDatetime() + "::Dif::" + diff).append(";");
								
								if(diff >= Long.parseLong(this.tblInitConfig.getTblParametersMap().get("MAX_QUERY_TIME"))) {
									
									PlatformDTO plataforma = platformService.getPlatformById(contract.getIdPlatform().getIdPlatform());
									
									contract.setIdStatus(2);
									contract.setLastQueryDatetime(Calendar.getInstance().getTime());
									contract.setStatusDatetime(Calendar.getInstance().getTime());
									boolean update = contractService.crearContrato(contract);
									msg.append("Contrato actualizado en Base de Datos::" + contract.getIdContract() + "::" + update).append(";");
									
									onbaseServiceController.notify(String.valueOf(contract.getIdContract()), responseToken.getAccess_token(),this.tblInitConfig.getTblParametersMap().get("NOT_ACCEPTED _RESPONSE"), plataforma.getUrl());
									
									responseDTO.setDescription("Contrato No Aceptado");
									responseDTO.setContractId(contract.getIdContract().toString());
									responseDTO.setContractStatus(status);
									
									msg.append("Contrato No Aceptado").append(";");
								}else {
									contract.setLastQueryDatetime(Calendar.getInstance().getTime());
									boolean update = contractService.crearContrato(contract);
									responseDTO.setDescription(" Contrato actualizado en Base de Datos " + contract.getIdContract());
									responseDTO.setContractId(contract.getIdContract().toString());
									responseDTO.setContractStatus(status);
									msg.append("Contrato actualizado en Base de Datos::" + contract.getIdContract() + "::" + update).append(";");
								}
								
							}else {
								responseDTO.setDescription("Contrato No encontrado en Base de Datos");
								msg.append("Contrato No encontrado en Base de Datos").append(";");
							}
							
						}
						
					}else {
						responseDTO.setDescription("No contiene campo :: [" + this.tblInitConfig.getTblParametersMap().get("QUERY_FIELD") + "]");
						msg.append("No contiene campo :: [" + this.tblInitConfig.getTblParametersMap().get("QUERY_FIELD") + "]").append(";");
					}
				}else {
					responseDTO.setDescription("Error consultado contrato en Onbase");
					msg.append("Error consultado contrato en Onbase").append(";");
				}
				
			}else {
				responseDTO.setDescription("HorusId es obligatorio");
				msg.append("HorusId es obligatorio").append(";");
			}
			
		}  catch (Exception e) {
			loggerError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), logDTO, responseDTO);
			return responseDTO;
		} finally {
			Long endTime = new Date().getTime();
			Long time = endTime-startTime;
			
			logDTO.setLevel(TypeLog.ORQ.name());
			logDTO.setResponseCode("" + 200);
			logDTO.setMsg("" + msg.toString());
			logDTO.setResponseTime("" + time);
			loggerService.log(logDTO);
		}
		
		return responseDTO;
		
	}
	
	/**
	 * @param code
	 * @param message
	 * @param logDTO
	 * @param response
	 * @param errorMessage
	 */
	private void loggerError(Integer code, String message, LogDTO logDTO, ResponseDTO response) {

		logDTO.setLevel(TypeLog.ERROR.name());
		logDTO.setResponseCode("" + code);
		logDTO.setMsg("" + message);
		loggerService.log(logDTO);
	}

}
