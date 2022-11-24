package gt.com.tigo.workeragreementrenewmanagement.dtos;

import java.io.Serializable;

import gt.com.tigo.workeragreementrenewmanagement.models.dto.PayloadDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class QueueResponseMessageDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String tipoProceso;
    private PayloadDTO payload;
    

    public QueueResponseMessageDTO() {

    }
}
