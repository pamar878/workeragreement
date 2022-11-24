package gt.com.tigo.workeragreementrenewmanagement.models.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import gt.com.tigo.workeragreementrenewmanagement.entities.TblParameters;
import gt.com.tigo.workeragreementrenewmanagement.models.dto.ParametersDTO;

@Mapper(componentModel = "spring")
public interface ParamsMapper {
	
	TblParameters toEntity(ParametersDTO source);
	List<ParametersDTO> toDto(List<TblParameters> target);

}
