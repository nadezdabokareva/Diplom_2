package diplom2.DTO.resposeDTO;

import diplom2.entity.User;
import lombok.Data;

@Data
public class ResponseUpdateUserDTO extends AbstractResponse {
    private User user;
}
