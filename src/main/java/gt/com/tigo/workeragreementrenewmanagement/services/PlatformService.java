package gt.com.tigo.workeragreementrenewmanagement.services;

import gt.com.tigo.workeragreementrenewmanagement.models.dto.PlatformDTO;

public interface PlatformService {

    PlatformDTO getPlatformBySource(String name);
    PlatformDTO getPlatformById(Integer id);
}
