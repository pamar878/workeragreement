package gt.com.tigo.workeragreementrenewmanagement.models.dto.payload;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
@ToString
public class AttributesObject implements Serializable{

	private String templateId;
	private String agreementType;
	private String documentId;

}
