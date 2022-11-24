package gt.com.tigo.workeragreementrenewmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import gt.com.tigo.workeragreementrenewmanagement.commons.util.ApiConstant;

@Configuration
@EnableWebMvc
@EnableConfigurationProperties
@Profile(value = {ApiConstant.LOCAL, ApiConstant.DEV, ApiConstant.LAB, ApiConstant.PDN})
public class AppBeanConfig {
  
  @Autowired Environment env;
  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.setSerializationInclusion(Include.NON_NULL);
    objectMapper.setSerializationInclusion(Include.NON_EMPTY);
    return objectMapper;
  }
  
  @Bean
  public MethodValidationPostProcessor methodValidationPostProcessor() {
    return new MethodValidationPostProcessor();
  }
  
}
