package diplom2.DTO.resposeDTO;

import io.qameta.allure.Story;
import lombok.Data;


@Data
@Story("Возврат сообщения об ошибке")
public class ErrorResponseDTO {
    private Boolean success;
    private String message;
}
