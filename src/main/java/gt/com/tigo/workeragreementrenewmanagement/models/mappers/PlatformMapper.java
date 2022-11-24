package gt.com.tigo.workeragreementrenewmanagement.models.mappers;

import org.mapstruct.Mapper;

import gt.com.tigo.workeragreementrenewmanagement.entities.TblPlatform;
import gt.com.tigo.workeragreementrenewmanagement.models.dto.PlatformDTO;

@Mapper(componentModel = "spring")
public interface PlatformMapper {
	
	TblPlatform toEntity(PlatformDTO source);
	PlatformDTO toDto(TblPlatform target);

}
