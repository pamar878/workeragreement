package gt.com.tigo.workeragreementrenewmanagement.commons.logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {
	
	/**
	 * Define si se habilita la data en el log.
	 */
	@Value("${logger.enable.data:true}")
	private boolean enabledData;
	
	/**
	 * Método que permite inicializar el CustomLogger.
	 * 
	 * @return Retorna un CustomLogger inicializado para ser usado en cualquier clase con la anotación {@literal @}Autowired.
	 */
	@Bean
	public CustomLogger initCustomLogger() {
		return new CustomLogger(LoggerConfig.class, enabledData);
	}
}
