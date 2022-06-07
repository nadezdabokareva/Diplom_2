package diplom2.DTO.resposeDTO;

import diplom2.entity.Order;
import lombok.Data;

@Data
public class ResponseOrderDTO extends AbstractResponse {
    private String name;
    private Order order;
}
