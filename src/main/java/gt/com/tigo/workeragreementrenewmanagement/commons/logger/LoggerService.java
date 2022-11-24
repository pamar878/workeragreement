package gt.com.tigo.workeragreementrenewmanagement.commons.logger;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gt.com.tigo.workeragreementrenewmanagement.models.dto.LogDTO;

@Component
public class LoggerService {

	/**
	 * Inyeccion del componente de registro generico.
	 */
	@Autowired
	private CustomLogger customLogger;

	public void log(LogDTO logDTO) {

		if(logDTO.getTime() == null) {
			logDTO.setTime( new Date().getTime() );
		}
		customLogger.logInfo(logDTO);
	}
	
}
