package gt.com.tigo.workeragreementrenewmanagement.services;

import java.util.List;

import gt.com.tigo.workeragreementrenewmanagement.models.dto.ContractDTO;

public interface ContractService {

    List<ContractDTO> getContractsByStatus();
    ContractDTO getContract(Integer id);
    boolean crearContrato(ContractDTO contract);
    ContractDTO getContractByHorusId(String idHorus);
}
