package diplom2.DTO.resposeDTO;

import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import lombok.Data;

@Data
@Story("Ответ об изменении данных пользователя")
public class ResponseUpdateUserDTO {
    private ResponseCreateUserDTO.ResponseUser user;
    private Boolean success;

    @Data
    @DisplayName("Возврат измененных данных пользователя")
    public static class ResponseUser {
        private String email;
        private String name;
    }
}
