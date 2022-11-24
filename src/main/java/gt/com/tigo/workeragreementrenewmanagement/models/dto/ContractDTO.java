package gt.com.tigo.workeragreementrenewmanagement.models.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ContractDTO {
	
	private Integer idContract;
    private Date requestDatetime;
    private Date lastQueryDatetime;
    private int idStatus;
    private Date statusDatetime;
    private String idHorus;
    private PlatformDTO idPlatform;

}
