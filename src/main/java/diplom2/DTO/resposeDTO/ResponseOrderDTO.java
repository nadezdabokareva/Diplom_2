package diplom2.DTO.resposeDTO;

import diplom2.entity.Order;
import io.qameta.allure.Story;
import lombok.Data;

@Data
@Story("Ответ о создании заказа")
public class ResponseOrderDTO {
    private Boolean success;
    private String name;
    private Order order;

}
