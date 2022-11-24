	package gt.com.tigo.workeragreementrenewmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/workermanagement/**").permitAll()
			.antMatchers(HttpMethod.GET, "/workermanagement/**").permitAll()
			.antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
			.antMatchers(HttpMethod.GET, "/swagger-ui.html/**").permitAll()
			.antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
			.antMatchers(HttpMethod.GET, "/configuration/**").permitAll()
			.antMatchers(HttpMethod.GET, "/configuration/ui").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
			.antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
			.anyRequest().authenticated();
	}
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
}
