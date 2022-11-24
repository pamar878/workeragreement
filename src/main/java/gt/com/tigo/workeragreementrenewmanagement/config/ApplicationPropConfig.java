package gt.com.tigo.workeragreementrenewmanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("rabbit")
public class ApplicationPropConfig {

	@Value("${rabbit.host}")
	public String rabbitHost;

	@Value("${rabbit.port}")
	public String rabbitPort;

	@Value("${rabbit.username}")
	public String rabbitUsername;

	@Value("${rabbit.password}")
	public String rabbitPassword;
	
	@Value("${rabbit.queue-response-name}")
	public String queueResponse;
	
	@Value("${rabbit.virtualhost}")
	public String rabbitVirtualhost;
	
	@Value("${rabbit.queue-read-time}")
	public long rabbitQueueReadTime;
	
	@Value("${externalServices.loginHiperionUrl}")
	public String loginHiperionUrl;
	
	@Value("${externalServices.queryContract}")
	public String queryContract;
	
	@Value("${externalServices.createContract}")
	public String createContract;
	
	@Value("${externalServices.clientId}")
	public String clientId;
	
	@Value("${externalServices.client_secret}")
	public String clientSecret;
	
	@Value("${externalServices.grant_type}")
	public String grantType;
	
	@Value("${externalServices.scope}")
	public String scope;

}
