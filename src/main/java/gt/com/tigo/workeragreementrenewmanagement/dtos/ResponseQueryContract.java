package gt.com.tigo.workeragreementrenewmanagement.dtos;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import gt.com.tigo.workeragreementrenewmanagement.models.dto.payload.AgreementItem;
import gt.com.tigo.workeragreementrenewmanagement.models.dto.payload.AttributesObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
@ToString
public class ResponseQueryContract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AttributesObject attributes;
	private ArrayList<AgreementItem> agreementItem = new ArrayList<AgreementItem>();
	private String pdf;

}
