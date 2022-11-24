package gt.com.tigo.workeragreementrenewmanagement.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import gt.com.tigo.workeragreementrenewmanagement.entities.TblPlatform;
import gt.com.tigo.workeragreementrenewmanagement.models.dto.PlatformDTO;
import gt.com.tigo.workeragreementrenewmanagement.models.mappers.PlatformMapper;
import gt.com.tigo.workeragreementrenewmanagement.models.repositories.PlatformRepository;

@Service(value = "platformService")
public class PlatformServiceImpl implements PlatformService {

    private final PlatformRepository platformRepository;
    @SuppressWarnings("unused")
	private final PlatformMapper platformMapper;
    
    public PlatformServiceImpl(PlatformRepository platformRepository,PlatformMapper platformMapper) {
		this.platformRepository = platformRepository;
		this.platformMapper = platformMapper;
	}
    
	@Override
	public PlatformDTO getPlatformBySource(String name) {
		
		Optional<TblPlatform> platform = platformRepository.findPlatformByName(name);
		if(!platform.isPresent()) {
			return null;
		}
		
		PlatformDTO dto = new PlatformDTO();
		dto.setAuthorizationToken(platform.get().getAuthorizationToken());
		dto.setIdPlatform(platform.get().getIdPlatform());
		dto.setIdStatus(platform.get().getIdStatus());
		dto.setName(platform.get().getName());
		dto.setUrl(platform.get().getUrl());
		
		return dto;
	}

	@Override
	public PlatformDTO getPlatformById(Integer id) {
		
		Optional<TblPlatform> platform = platformRepository.findById(id);
		if(!platform.isPresent()) {
			return null;
		}
		
		PlatformDTO dto = new PlatformDTO();
		dto.setAuthorizationToken(platform.get().getAuthorizationToken());
		dto.setIdPlatform(platform.get().getIdPlatform());
		dto.setIdStatus(platform.get().getIdStatus());
		dto.setName(platform.get().getName());
		dto.setUrl(platform.get().getUrl());
		
		return dto;
		
	}
    
}
