package gt.com.tigo.workeragreementrenewmanagement.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
@ToString
public class ResponseCreateContract implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataResponseCreateContract data;

}
