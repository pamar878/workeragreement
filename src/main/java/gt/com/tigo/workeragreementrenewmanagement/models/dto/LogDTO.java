package gt.com.tigo.workeragreementrenewmanagement.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class LogDTO {

	private String name;
	private String hostname;
	private String apiKey;
	private String uri;
	private String responseCode;
	private String responseTime;
	private String clientIP;
	private String pid;
	private String level;
	private String msg;
	private Long time;
	private String v;

}
