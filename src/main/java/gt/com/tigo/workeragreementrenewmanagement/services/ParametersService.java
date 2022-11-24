package gt.com.tigo.workeragreementrenewmanagement.services;

import java.util.List;

import gt.com.tigo.workeragreementrenewmanagement.models.dto.ParametersDTO;

public interface ParametersService {

    List<ParametersDTO> getParameters();
    ParametersDTO getParameter(Long id);
}
