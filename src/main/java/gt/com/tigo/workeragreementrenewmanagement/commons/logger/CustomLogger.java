package gt.com.tigo.workeragreementrenewmanagement.commons.logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import gt.com.tigo.workeragreementrenewmanagement.models.dto.LogDTO;
 
public class CustomLogger {

	/**
	 * Lista de referencia de loggers.
	 */
	private static ConcurrentHashMap<String, Logger> loggers = new ConcurrentHashMap<>();

	/**
	 * Define si está habilitado el log de aplicación. Por defecto queda inhabilitado.
	 */
	@SuppressWarnings("unused")
	private boolean enabledData = false;

	/**
	 * Logger de ejecución definido en el log4j2.xml. Por defecto queda inicializado en: APP_LOG
	 */
	private String appLogger = "APP_LOG";

	/**
	 * Metodo constructor de la clase en cuestión.
	 * 
	 * @param classToCreateLogger Clase con la que se creará el defaultLogger.
	 * @param enabledData Define si se habilita el log de aplicación o no.
	 */
	public CustomLogger(Class<?> classToCreateLogger, boolean enabledData) {
		super();
		this.enabledData = enabledData;
	}

	/**
	 * Metodo que permite obtener un logger de la lista de logger de la clase si existe, si no existe creará uno con el LogManager de Log4j.
	 * @version 1.0
	 *
	 * @param loggerName Nombre del log a obtener.
	 * @return Retorna un org.apache.logging.log4j.Logger.
	 */
	private static Logger getLogger(String loggerName) {
		return loggers.computeIfAbsent(loggerName, k -> LogManager.getLogger(loggerName));
	}
	
	public void logInfo(LogDTO logDTO) {
		log(Level.INFO, logDTO);
	}
	
	/**
	 * 
	 * @param level Nivel de severidad (Ejemplo: org.apache.logging.log4j.Level.INFO, org.apache.logging.log4j.Level.ERROR)
	 * @param logDTO Objeto que contiene la informacion.
	 */
	public void log(Level level, LogDTO logDTO) {

		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		putThreadContext("name", "{\"name\":\"WorkerAgreementManagement\",");
		putThreadContext("hostname", "\"hostname\":\"" + logDTO.getHostname() + "\",");
		putThreadContext("apiKey", "\"apiKey\":\"" + logDTO.getApiKey() + "\",");
		putThreadContext("uri", "\"uri\":\"" + logDTO.getUri() + ",");
		putThreadContext("responseCode", "\"responseCode\":" + logDTO.getResponseCode() + ",");
		putThreadContext("responseTime", "\"responseTime\":" + logDTO.getResponseTime() + ",");
		putThreadContext("clientIP", "\"clientIP\":\"" + logDTO.getClientIP() + "\",");
		putThreadContext("pid", "\"pid\":" + 1 + ",");
		putThreadContext("level", "\"level\":" + logDTO.getLevel() + ",");
		putThreadContext("msg", "\"msg\":\"" + logDTO.getMsg() + "\",");
		putThreadContext("time", "\"time\":\"" + logDTO.getTime() != null ? format.format(new Date(logDTO.getTime())) : "" + "\",");
		putThreadContext("v", "\"v\":0}");

		try {
			putThreadContext("HOSTNAME", InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) { 
			putThreadContext("HOSTNAME", "");
		}
		
		Logger logger = getLogger(appLogger);
		logger.log(level, "");
		ThreadContext.clearAll();
	}

	/**
	 * 
	 * Metodo que permite asignar un determinado parametro al contexto de log.
	 * 
	 * @param key, clave a asignar al log
	 * @param param, valor a asignar al log
	 */
	private void putThreadContext(String key, String param) {
		if (param != null) {
			ThreadContext.put(key, param);
		}
	}
	
}
