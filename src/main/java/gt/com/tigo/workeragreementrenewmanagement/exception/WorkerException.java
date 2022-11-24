package gt.com.tigo.workeragreementrenewmanagement.exception;

import org.apache.log4j.Logger;

/**
 * Exception from bussines
 *
 */
public class WorkerException extends Exception  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger LOGGER = Logger.getLogger(getClass());

	public WorkerException(String message, Throwable e) {
		super(message, e);
		LOGGER.error(e.getMessage(), e);
	}
	
	public WorkerException() {
		super("ERROR_EXCEPTION_SERVER");
	}

	public WorkerException(String message) {
		super(message);
	}
}
