package gt.com.tigo.workeragreementrenewmanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import gt.com.tigo.workeragreementrenewmanagement.models.dto.ParametersDTO;
import gt.com.tigo.workeragreementrenewmanagement.models.mappers.ParamsMapper;
import gt.com.tigo.workeragreementrenewmanagement.models.repositories.ParametersRepository;

@Service(value = "parametersService")
public class ParametersServiceImpl implements ParametersService {

    private final ParametersRepository parametersRepository;
    private final ParamsMapper parametersMapper;

    public ParametersServiceImpl(ParametersRepository parametersRepository,ParamsMapper parametersMapper){
        this.parametersRepository = parametersRepository;
        this.parametersMapper = parametersMapper;
    }

	@Override
	public List<ParametersDTO> getParameters() {

		return parametersMapper.toDto(parametersRepository.findAll());
	}

	@Override
	public ParametersDTO getParameter(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

    
}
