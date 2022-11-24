package gt.com.tigo.workeragreementrenewmanagement.thread;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import gt.com.tigo.workeragreementrenewmanagement.commons.rabbitmq.MessageSendReceiveRabbit;
import gt.com.tigo.workeragreementrenewmanagement.commons.util.ApiConstant;
import gt.com.tigo.workeragreementrenewmanagement.config.ApplicationPropConfig;
import gt.com.tigo.workeragreementrenewmanagement.config.TblInitDBConfiguration;
import gt.com.tigo.workeragreementrenewmanagement.dtos.QueueResponseMessageDTO;
import gt.com.tigo.workeragreementrenewmanagement.dtos.ResponseDTO;
import gt.com.tigo.workeragreementrenewmanagement.models.dto.ContractDTO;
import gt.com.tigo.workeragreementrenewmanagement.rest.WorkerBusinessController;
import gt.com.tigo.workeragreementrenewmanagement.services.ContractService;

public class ControllerWorkerThread implements Callable<Boolean> {
	
	private final Logger LOGGER = Logger.getLogger(getClass());

	private boolean processStarted = true;
	
	private ApplicationPropConfig applicationPropConfig;
	
	private TblInitDBConfiguration tblInitConfig;
	
	private WorkerBusinessController workerBusinessController;
	
	private String typeProcess;
	
	private ContractService contractService;

	
	public ControllerWorkerThread (ApplicationPropConfig applicationPropConfig,TblInitDBConfiguration tblInitConfig, 
			boolean processStarted,WorkerBusinessController workerBusinessController, String typeProcess, ContractService contractService) {
		this.applicationPropConfig = applicationPropConfig;
		this.tblInitConfig = tblInitConfig;
		this.processStarted = processStarted;
		this.workerBusinessController = workerBusinessController;
		this.typeProcess = typeProcess;
		this.contractService = contractService;
	}

	@Override
	public Boolean call() throws Exception {

		try {
			if(typeProcess.equals(ApiConstant.READ_QUEUE)) {
				readQueue();
			}
			
			if(typeProcess.equals(ApiConstant.RETRY)) {
				retryContracts();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}
	
	/**
	 * 
	 * */
	public void readQueue() {
		LOGGER.info("WorkerAgreementRenewal - ::Proceso lectura colaMQ::");

		MessageSendReceiveRabbit msg = new MessageSendReceiveRabbit(this.applicationPropConfig.rabbitVirtualhost, this.applicationPropConfig.rabbitHost, this.applicationPropConfig.rabbitPort, this.applicationPropConfig.rabbitUsername, this.applicationPropConfig.rabbitPassword, this.applicationPropConfig.queueResponse);

		while (this.processStarted) {
			String message = msg.readMessage(this.applicationPropConfig.queueResponse);
			if (!message.equals("")) {
				LOGGER.info("Message received: "
						+ message.replace("  ", " ").replace("\r", "").replace("\n", "").replace("\t", ""));
				
				try {
					
					QueueResponseMessageDTO dto = processMessage(message);
					
					if(dto.getTipoProceso().equals(ApiConstant.QUERY_CONTRACT)){
						
						ResponseDTO responseDTO = workerBusinessController.queryContract(dto.getPayload().getDocumentId());
						LOGGER.error("Reintento horusId [" + dto.getPayload().getDocumentId() + "] -> "
								+ responseDTO.getDescription() + " Status: "
								+ responseDTO.getContractStatus());
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error("Error processing message: '" + message + "'");
				}
				
			}else {
				LOGGER.info("Cola sin mensajes");
				try {
					Thread.sleep(Long.valueOf(this.tblInitConfig.getTblParametersMap().get("QUEUE_WAIT_TIME")));
					
				} catch (InterruptedException e) {
					
				}
			}
		}
		msg.destroy();
		LOGGER.info("WorkerAgreementRenewal - ::Proceso lectura colaMQ finalizado::");
	}
	
	/**
	 * 
	 * */
	public void retryContracts() {
		
		LOGGER.info("WorkerAgreementRenewal - ::Proceso RetryContracts::");
		
		while (this.processStarted) {
			
			List<ContractDTO> pendingsContracts = contractService.getContractsByStatus();
			LOGGER.info("Contractos a reintentar: " + pendingsContracts.size());
			
			if (pendingsContracts.size() > 0) {
				try {
					
					for (ContractDTO contractDTO : pendingsContracts) {
						LOGGER.info("ContractID: " + contractDTO.getIdContract() + " - HorusID: " + contractDTO.getIdHorus());
						ResponseDTO responseDTO = workerBusinessController.queryContract(contractDTO.getIdHorus());
						LOGGER.error("Reintento horusId [" + contractDTO.getIdHorus() + "] -> "
								+ responseDTO.getDescription() + " Status: "
								+ responseDTO.getContractStatus());
					}
					
					LOGGER.info("No hay mensajes a reintentar");
					Thread.sleep(Long.valueOf(this.tblInitConfig.getTblParametersMap().get("TIME_WAIT_RETRY")));
					
				}catch (Exception e) {
					LOGGER.info("Error reintentando " + e.getMessage());
				}
				
				
			}else {
				LOGGER.info("No hay mensajes a reintentar");
				try {
					Thread.sleep(Long.valueOf(this.tblInitConfig.getTblParametersMap().get("TIME_WAIT_RETRY")));
					
				} catch (InterruptedException e) {
					
				}
			}
			
		}
		
		LOGGER.info("WorkerAgreementRenewal - ::Proceso RetryContracts::");
		
	}
	
	private QueueResponseMessageDTO processMessage(String message) {
		QueueResponseMessageDTO messageResponse = new QueueResponseMessageDTO();

		Gson gson = new Gson();
		messageResponse = gson.fromJson(message, QueueResponseMessageDTO.class);

		return messageResponse;
	}

	public boolean isProcessStarted() {
		return processStarted;
	}

	public void setProcessStarted(boolean processStarted) {
		this.processStarted = processStarted;
	}

	public ApplicationPropConfig getApplicationPropConfig() {
		return applicationPropConfig;
	}

	public void setApplicationPropConfig(ApplicationPropConfig applicationPropConfig) {
		this.applicationPropConfig = applicationPropConfig;
	}

	public TblInitDBConfiguration getTblInitConfig() {
		return tblInitConfig;
	}

	public void setTblInitConfig(TblInitDBConfiguration tblInitConfig) {
		this.tblInitConfig = tblInitConfig;
	}

}

