package gt.com.tigo.workeragreementrenewmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class RequestNotify {
	
	private String documentId;
	private String transactionStatus;
	private String documentNote;

}
