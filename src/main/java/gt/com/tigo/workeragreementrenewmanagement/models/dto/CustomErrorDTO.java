
package gt.com.tigo.workeragreementrenewmanagement.models.dto;

import java.text.MessageFormat;

/**
 *
 * @author nsoto Clase para definir los errores personalizados. Aquí se pueden
 *         definir los formatos requeridos para retornar cuando se encuentran
 *         errores.
 */
public class CustomErrorDTO {
	private Integer errorCode;
	private String errorType;
	private String code;
	private String description;

	/**
	 * @return the errorCode
	 */
	public Integer getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorType
	 */
	public String getErrorType() {
		return errorType;
	}

	/**
	 * @param errorType the errorType to set
	 */
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return MessageFormat.format("ErrorCode {0}, ErrorType {1}, Code {2}, Description {3}", errorCode, errorType,
				code, description);
	}

}
