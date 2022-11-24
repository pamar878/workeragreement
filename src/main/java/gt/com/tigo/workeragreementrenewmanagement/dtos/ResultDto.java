package gt.com.tigo.workeragreementrenewmanagement.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ResultDto implements Serializable {

    private static final long serialVersionUID = -4278430909224292944L;
    private int codigo;
    private String descripcion;

}
