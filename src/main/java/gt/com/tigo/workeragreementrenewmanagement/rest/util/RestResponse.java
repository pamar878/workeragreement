package gt.com.tigo.workeragreementrenewmanagement.rest.util;

import java.util.Map;

/**
 * Clase encargada de la respuesta de los servicios Rest
 *
 * @author paulo.martinez@ingeneo.com.co <br>
 * 
 */
public class RestResponse<R,T> {

	private R bodyResponse;
	private T errorResponse;
	private Map<String, String> headerResponse;
	private int code;

	/**
	 * Método que obtiene el valor de bodyResponse
	 *
	 * @return bodyResponse, valor a obtener
	 */
	public R getBodyResponse() {
		return bodyResponse;
	}

	/**
	 * Método que obtiene el valor de headerResponse
	 *
	 * @return headerResponse, valor a obtener
	 */
	public Map<String, String> getHeaderResponse() {
		return headerResponse;
	}

	/**
	 * Método que obtiene el valor de code
	 *
	 * @return code, valor a obtener
	 */
	public int getCode() {
		return code;
	}

	public void setBodyResponse(R bodyResponse) {
		this.bodyResponse = bodyResponse;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setHeaderResponse(Map<String, String> headerResponse) {
		this.headerResponse = headerResponse;
	}
	
	/**
	 * Metodo que retorna el valor del atributo errorResponse
	 *
	 * @author paulo.martinez@ingeneo.com.co <br>
	 * 
	 * @return 	errorResponse
	 */
	public T getErrorResponse() {
		return errorResponse;
	}

	
	/**
	 * Metodo que permite almacenar el valor errorResponse en el atributo errorResponse
	 *
	 * @author paulo.martinez@ingeneo.com.co <br>
	 * 
	 * @param errorResponse el valor a asignar al atributo errorResponse
	 */
	public void setErrorResponse(T errorResponse) {
		this.errorResponse = errorResponse;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RestResponse [bodyResponse=");
		builder.append(bodyResponse);
		builder.append(", errorResponse=");
		builder.append(errorResponse);
		builder.append(", headerResponse=");
		builder.append(headerResponse);
		builder.append(", code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}

	

}
