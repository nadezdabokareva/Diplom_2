package diplom2.DTO.resposeDTO;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractResponse implements Serializable {
    private Boolean success;
    private String message;
}
