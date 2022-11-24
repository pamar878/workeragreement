package gt.com.tigo.workeragreementrenewmanagement.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseDTO implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
	private String contractId;
	private String contractStatus;

}
