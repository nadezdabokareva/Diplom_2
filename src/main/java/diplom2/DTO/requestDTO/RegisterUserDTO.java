package diplom2.DTO.requestDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RegisterUserDTO implements Serializable {
    private String email;
    private String password;
    private String name;
}
