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
public class AgreementItem implements Serializable {

	private String name;
	private String value;
	private String type;

}
