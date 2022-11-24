package gt.com.tigo.workeragreementrenewmanagement.models.mappers;

import org.mapstruct.Mapper;

import gt.com.tigo.workeragreementrenewmanagement.entities.TblContract;
import gt.com.tigo.workeragreementrenewmanagement.models.dto.ContractDTO;

@Mapper(componentModel = "spring")
public interface ContractMapper {
	
	TblContract toEntity(ContractDTO source);
	ContractDTO toDto(TblContract target);

}
