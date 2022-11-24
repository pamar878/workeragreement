package gt.com.tigo.workeragreementrenewmanagement.api;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gt.com.tigo.workeragreementrenewmanagement.commons.logger.LoggerService;
import gt.com.tigo.workeragreementrenewmanagement.commons.util.TypeLog;
import gt.com.tigo.workeragreementrenewmanagement.commons.util.Utilities;
import gt.com.tigo.workeragreementrenewmanagement.dtos.ResponseDTO;
import gt.com.tigo.workeragreementrenewmanagement.dtos.ResultDto;
import gt.com.tigo.workeragreementrenewmanagement.models.dto.LogDTO;
import gt.com.tigo.workeragreementrenewmanagement.models.dto.PayloadDTO;
import gt.com.tigo.workeragreementrenewmanagement.rest.WorkerBusinessController;

@RestController
@RequestMapping("workermanagement")
public class WorkerApiController {

	@Autowired
	private LoggerService loggerService;

	@Autowired
	private WorkerBusinessController workerBusinessController;
	
	@Autowired
	private Utilities utilities;

	@GetMapping(path = "/agreement/{idContract}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> queryContract(@PathVariable(name = "idContract") String idContract,
			HttpServletRequest request) {
		
		Long startTime = new Date().getTime();
		LogDTO logDTO = LogDTO.builder().apiKey("queryContractApi?"+idContract).uri(request.getRequestURL().toString()).
				clientIP(request.getRemoteAddr()).time(startTime).build();

		ResponseDTO responseDTO = new ResponseDTO();

		try {

			if(idContract == null || idContract.isEmpty()) {
				loggerError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "", logDTO, responseDTO);
				return new ResponseEntity<Object>(utilities.error(400, "idContract es obligatorio"), HttpStatus.BAD_REQUEST);
			}
			
			responseDTO = workerBusinessController.queryContract(idContract);
			
			if(responseDTO == null){
                return new ResponseEntity<Object>(utilities.error(403, ""), HttpStatus.FORBIDDEN);
            }

		} catch (Exception e) {
			responseDTO.setDescription("Error realizando la consulta ::" + e.getMessage());
			loggerError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), logDTO, responseDTO);
			return new ResponseEntity<>(utilities.error(500, ""), HttpStatus.INTERNAL_SERVER_ERROR);
		}finally {
			
			Long endTime = new Date().getTime();
			Long time = endTime-startTime;
			
			logDTO.setLevel(TypeLog.EXP.name());
			logDTO.setResponseCode("" + 200);
			logDTO.setMsg("" + responseDTO.getDescription());
			logDTO.setResponseTime("" + time);
			loggerService.log(logDTO);
		}

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@PostMapping(path = "/agreement", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createContract(@RequestBody PayloadDTO payload,
			HttpServletRequest request) {

		Long startTime = new Date().getTime();
		LogDTO logDTO = LogDTO.builder().apiKey("createContractApi?"+payload.getData().getAttributes().getDocumentId()).uri(request.getRequestURL().toString()).
				clientIP(request.getRemoteAddr()).time(startTime).build();
		
		ResponseDTO responseDTO = new ResponseDTO();

		try {

			if(payload.getData().getAttributes().getDocumentId() == null || payload.getData().getAttributes().getDocumentId().isEmpty()) {
				loggerError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "", logDTO, responseDTO);
				return new ResponseEntity<Object>(utilities.error(400, ""), HttpStatus.BAD_REQUEST);
			}
			
			responseDTO = workerBusinessController.createContract(payload);
			
			if(responseDTO == null){
                return new ResponseEntity<Object>(utilities.error(403, ""), HttpStatus.FORBIDDEN);
            }

		} catch (Exception e) {
			ResultDto result = new ResultDto();
			result.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			result.setDescripcion("Error creando el contrato ::" + e.getMessage());
			loggerError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), logDTO, responseDTO);
			return new ResponseEntity<>(utilities.error(500, ""), HttpStatus.INTERNAL_SERVER_ERROR);
		}finally {
			
			Long endTime = new Date().getTime();
			Long time = endTime-startTime;
			
			logDTO.setLevel(TypeLog.EXP.name());
			logDTO.setResponseCode("" + 200);
			logDTO.setMsg("" + responseDTO.getDescription());
			logDTO.setResponseTime("" + time);
			loggerService.log(logDTO);
		}

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
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
