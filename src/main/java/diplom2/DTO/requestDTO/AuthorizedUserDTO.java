package diplom2.DTO.requestDTO;

import diplom2.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthorizedUserDTO implements Serializable {
    private String email;
    private String password;

    public AuthorizedUserDTO(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
