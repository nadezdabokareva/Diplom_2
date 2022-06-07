package diplom2.DTO.requestDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RefreshTokenDTO implements Serializable {
    private String token;
}
