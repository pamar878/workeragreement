package gt.com.tigo.workeragreementrenewmanagement.models.dto;

import java.util.Collection;

import lombok.Data;

@Data
public class PlatformDTO {
	
	private Integer idPlatform;
    private int idStatus;
    private String name;
    private String url;
    private String authorizationToken;
    private Collection<ContractDTO> tblContractCollection;

}
