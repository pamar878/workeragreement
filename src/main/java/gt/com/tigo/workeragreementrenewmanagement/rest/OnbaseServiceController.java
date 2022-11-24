package gt.com.tigo.workeragreementrenewmanagement.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

import gt.com.tigo.workeragreementrenewmanagement.commons.logger.LoggerService;
import gt.com.tigo.workeragreementrenewmanagement.commons.util.TypeLog;
import gt.com.tigo.workeragreementrenewmanagement.config.ApplicationPropConfig;
import gt.com.tigo.workeragreementrenewmanagement.config.TblInitDBConfiguration;
import gt.com.tigo.workeragreementrenewmanagement.dtos.OauthOnbaseResponse;
import gt.com.tigo.workeragreementrenewmanagement.dtos.RequestNotify;
import gt.com.tigo.workeragreementrenewmanagement.dtos.ResponseCreateContract;
import gt.com.tigo.workeragreementrenewmanagement.dtos.ResponseDTO;
import gt.com.tigo.workeragreementrenewmanagement.dtos.ResponseQueryContract;
import gt.com.tigo.workeragreementrenewmanagement.models.dto.LogDTO;
import gt.com.tigo.workeragreementrenewmanagement.models.dto.PayloadDTO;

@Controller
public class OnbaseServiceController {
	
	@Autowired
	private LoggerService loggerService;
	
	@Autowired
	private ApplicationPropConfig applicationPropConfig;
	
	@Autowired
	private TblInitDBConfiguration tblInitConfig;
	
	/**
	 * Consulta token de seguridad a onbase
	 * 
	 * */
	public OauthOnbaseResponse getToken () {
		
		Long startTime = new Date().getTime();
		LogDTO logDTO = LogDTO.builder().apiKey("getToken")
				.time(startTime).build();
		
		Gson gson = new Gson();
		int timeout = Integer.parseInt(this.tblInitConfig.getTblParametersMap().get("TIMEOUT"));
		
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectTimeout(timeout);
		httpRequestFactory.setReadTimeout(timeout);
		RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
		paramsMap.add("client_id", applicationPropConfig.clientId);
		paramsMap.add("client_secret", applicationPropConfig.clientSecret);
		paramsMap.add("grant_type", applicationPropConfig.grantType);
		paramsMap.add("scope", applicationPropConfig.scope);
		
		HttpEntity<MultiValueMap<String, String>> requestHttpEntity = new HttpEntity<>(paramsMap, headers);
		
		ResponseEntity<Object> response = restTemplate.exchange(applicationPropConfig.loginHiperionUrl, HttpMethod.POST,
				requestHttpEntity, Object.class);
		
		Long endTime = new Date().getTime();
		Long time = endTime-startTime;
		
		logDTO.setLevel(TypeLog.TRACE.name());
		logDTO.setResponseCode("" + 200);
		logDTO.setMsg("" + response.toString());
		logDTO.setResponseTime("" + time);
		loggerService.log(logDTO);
		
		return gson.fromJson(response.getBody().toString(), OauthOnbaseResponse.class);
		
	}
	
	/**
	 * Crea el Contrato onbase
	 * 
	 * */
	public ResponseCreateContract createContract (PayloadDTO payload, String bearerToken) {
		
		int timeout = Integer.parseInt(this.tblInitConfig.getTblParametersMap().get("TIMEOUT"));
		
		Long startTime = new Date().getTime();
		LogDTO logDTO = LogDTO.builder().apiKey("createContract Onbase")
				.time(startTime).build();
		
		StringBuilder msg = new StringBuilder();
		
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectTimeout(timeout);
		httpRequestFactory.setReadTimeout(timeout);
		
		RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(bearerToken);
		
		HttpEntity<PayloadDTO> requestHttpEntity = new HttpEntity<>(payload,headers);
		
		try {
			
			ResponseEntity<ResponseCreateContract> response = restTemplate.exchange(applicationPropConfig.createContract, HttpMethod.POST,
					requestHttpEntity, ResponseCreateContract.class);
			
			msg.append(response.getBody().toString());
			
			return response.getBody();
		}catch (Exception e) {
			loggerError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), logDTO, null);
			return null;
		}finally {
			
			Long endTime = new Date().getTime();
			Long time = endTime-startTime;
			
			logDTO.setLevel(TypeLog.TRACE.name());
			logDTO.setResponseCode("" + 200);
			logDTO.setMsg("" + msg.toString());
			logDTO.setResponseTime("" + time);
			loggerService.log(logDTO);
			
		}
		
	}
	
	/**
	 * Consulta el Contrato onbase
	 * 
	 * */
	public ResponseQueryContract queryContract (String ccHorus, String bearerToken) {
		
		int timeout = Integer.parseInt(this.tblInitConfig.getTblParametersMap().get("TIMEOUT"));
		
		Long startTime = new Date().getTime();
		LogDTO logDTO = LogDTO.builder().apiKey("queryContract Onbase")
				.time(startTime).build();
		
		StringBuilder msg = new StringBuilder();
		
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectTimeout(timeout);
		httpRequestFactory.setReadTimeout(timeout);
		RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(bearerToken);
		
		String url = applicationPropConfig.queryContract + "/" + ccHorus;
		String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("keywordName", "{keywordName}")
		        .queryParam("type", "{type}")
		        .encode()
		        .toUriString();
		
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("keywordName", "CC_horus_id");
		paramsMap.put("type", "pdf");
		
		HttpEntity<MultiValueMap<String, String>> requestHttpEntity = new HttpEntity<>(headers);
		
		try {
			ResponseEntity<ResponseQueryContract> response = restTemplate.exchange(urlTemplate, HttpMethod.GET,
					requestHttpEntity, ResponseQueryContract.class,paramsMap);
			
			msg.append("Contrato Consultado Exitosamente");
			
			return response.getBody();
		}catch (Exception e) {
			loggerError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), logDTO, null);
			return null;
		}finally {
			
			Long endTime = new Date().getTime();
			Long time = endTime-startTime;
			
			logDTO.setLevel(TypeLog.TRACE.name());
			logDTO.setResponseCode("" + 200);
			logDTO.setMsg("" + msg.toString());
			logDTO.setResponseTime("" + time);
			loggerService.log(logDTO);
			
		}
		
	}
	
	/**
	 * norificar a terceros
	 * 
	 * */
	public Object notify (String documentId, String bearerToken, String status, String url) {
		
		int timeout = Integer.parseInt(this.tblInitConfig.getTblParametersMap().get("TIMEOUT"));
		
		Long startTime = new Date().getTime();
		LogDTO logDTO = LogDTO.builder().apiKey("notify Onbase")
				.time(startTime).build();
		
		StringBuilder msg = new StringBuilder();
		
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectTimeout(timeout);
		httpRequestFactory.setReadTimeout(timeout);
		RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(bearerToken);
		
		RequestNotify notify = new RequestNotify(documentId, status, "File processed successfully");
		
		HttpEntity<RequestNotify> requestHttpEntity = new HttpEntity<>(notify,headers);
		
		try {
			ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST,
					requestHttpEntity, Object.class);
			
			msg.append(response.getBody().toString());
			
			return response;
		}catch (Exception e) {
			loggerError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), logDTO, null);
			return null;
		}finally {
			
			Long endTime = new Date().getTime();
			Long time = endTime-startTime;
			
			logDTO.setLevel(TypeLog.TRACE.name());
			logDTO.setResponseCode("" + 200);
			logDTO.setMsg("" + msg.toString());
			logDTO.setResponseTime("" + time);
			loggerService.log(logDTO);
			
		}
		
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
