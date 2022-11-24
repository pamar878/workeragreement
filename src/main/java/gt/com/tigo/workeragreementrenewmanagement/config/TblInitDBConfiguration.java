package gt.com.tigo.workeragreementrenewmanagement.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gt.com.tigo.workeragreementrenewmanagement.services.ParametersService;

@Component
public class TblInitDBConfiguration {

	private Map<String, String> tblParametersMap;

	private final Logger LOGGER = Logger.getLogger(getClass());
	
	@Autowired
	private ParametersService parametersService;

	@PostConstruct
	private void getInitParameters() throws Exception {

		tblParametersMap = new HashMap<String, String>();

		try {
			
			parametersService.getParameters().forEach(gr -> tblParametersMap.put(gr.getName(), gr.getValue()));
			LOGGER.info("WorkerAgreementRenewal - parameters:: " + tblParametersMap.toString());

		} catch (Exception ex) {
			LOGGER.info("WorkerAgreementRenewal - getInitParameters - process Finished");

		} 

	}

	public Map<String, String> getTblParametersMap() {
		return tblParametersMap;
	}


}
