package diplom2.entity;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Jacksonized
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient implements Serializable {
    private String _id;
    private String name;
    private String type;
    private Long proteins;
    private Long fat;
    private Long carbohydrates;
    private Long calories;
    private Long price;
    private String image;
    private String image_mobile;;
    private String image_large;
    private Long __v;

}
