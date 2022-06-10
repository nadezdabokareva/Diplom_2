package diplom2.DTO.resposeDTO;

import io.qameta.allure.Story;
import lombok.Data;

@Data
@Story("Response about user registration")
public class ResponseAuthorizationDTO {

    private String accessToken;
    private String refreshToken;
    private ResponseCreateUserDTO.ResponseUser user;
    private Boolean success;

    @Data
    public static class ResponseUser {
        private String email;
        private String name;
    }
}
