package gt.com.tigo.workeragreementrenewmanagement.rest.util;

/**
 * Clase encargada de la excepcion de peticiones rest.
  * 
 * @author paulo.martinez@ingeneo.com.co <br>
 * 
 */
public class RestException extends RuntimeException {

	/**
	 * Version de la clase
	 */
	private static final long serialVersionUID = 1L;
	private final String message;
	private final int status;
	private final String response;

	/**
	 * 
	 * Constructor de la clase.
	 * 
	 * @param message,  mensaje de la peticion.
	 * @param status,   estado de la peticion.
	 * @param response, respuesta de la peticion.
	 */
	public RestException(String message, int status, String response) {
		super(message);
		this.message = message;
		this.status = status;
		this.response = response;
	}

	/**
	 * Método que obtiene el valor de message
	 *
	 * @return message, valor a obtener
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * Método que obtiene el valor de status
	 *
	 * @return status, valor a obtener
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Método que obtiene el valor de response
	 *
	 * @return response, valor a obtener
	 */
	public String getResponse() {
		return response;
	}

}
