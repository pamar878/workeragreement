package gt.com.tigo.workeragreementrenewmanagement.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class OauthOnbaseResponse implements Serializable{
	
	private String token_type;
	private String access_token;
	private String expires_in;

}
