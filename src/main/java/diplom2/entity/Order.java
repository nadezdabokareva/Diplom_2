package diplom2.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class Order {
    private List<Ingredient> ingredientList;
    private String name;
    private String _id;
    private String status;
    private Long number;
    private Date createdAt;
    private Date updatedAt;
}
