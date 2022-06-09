package diplom2.DTO.resposeDTO;

import diplom2.entity.Order;
import io.qameta.allure.Story;
import lombok.Data;

import java.util.List;

@Data
@Story("Ответ на получение заказа")
public class ResponseGetOrders {
    private Boolean success;
    private List<Order> orders;
    private Long total;
    private Long totalToday;
}
