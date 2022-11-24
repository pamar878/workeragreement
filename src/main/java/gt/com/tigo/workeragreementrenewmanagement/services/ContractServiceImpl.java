package gt.com.tigo.workeragreementrenewmanagement.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import gt.com.tigo.workeragreementrenewmanagement.entities.TblContract;
import gt.com.tigo.workeragreementrenewmanagement.models.dto.ContractDTO;
import gt.com.tigo.workeragreementrenewmanagement.models.dto.PlatformDTO;
import gt.com.tigo.workeragreementrenewmanagement.models.mappers.ContractMapper;
import gt.com.tigo.workeragreementrenewmanagement.models.repositories.ContractRepository;

@Service(value = "contractService")
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;

    public ContractServiceImpl(ContractRepository contractRepository,ContractMapper contractMapper){
        this.contractRepository = contractRepository;
        this.contractMapper = contractMapper;
    }

	@Override
	public List<ContractDTO> getContractsByStatus() {
		
		List<ContractDTO> listContracts = new ArrayList<ContractDTO>();
		List<TblContract> contracts = contractRepository.findByStatusId(0);
		
		for (TblContract tblContract : contracts) {
			ContractDTO temp = new ContractDTO();
			temp.setIdContract(tblContract.getIdContract());
			temp.setIdHorus(tblContract.getIdHorus());
			listContracts.add(temp);
		}
		
		return listContracts;
	}

	@Override
	public ContractDTO getContract(Integer id) {
		Optional<TblContract> contract = contractRepository.findById(id);
		if(!contract.isPresent()) {
			return null;
		}
		return contractMapper.toDto(contract.get());
	}

	@Override
	public boolean crearContrato(ContractDTO contract) {
		contractRepository.save(contractMapper.toEntity(contract));
		return true;
	}

	@Override
	public ContractDTO getContractByHorusId(String idHorus) {
		Optional<TblContract> contract = contractRepository.findByHorusId(idHorus);
		if(!contract.isPresent()) {
			return null;
		}
		
		ContractDTO dto = new ContractDTO();
		dto.setIdHorus(contract.get().getIdHorus());
		dto.setIdContract(contract.get().getIdContract());
		dto.setIdStatus(contract.get().getIdStatus());
		dto.setLastQueryDatetime(contract.get().getLastQueryDatetime());
		dto.setRequestDatetime(contract.get().getRequestDatetime());
		dto.setStatusDatetime(contract.get().getStatusDatetime());
		
		PlatformDTO platformDTO = new PlatformDTO();
		platformDTO.setIdPlatform(contract.get().getIdPlatform().getIdPlatform());
		
		dto.setIdPlatform(platformDTO);
		
		return dto;
	}
    
}
