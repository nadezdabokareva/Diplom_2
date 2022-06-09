package diplom2.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.qameta.allure.Story;

import lombok.Data;

import java.io.Serializable;

@Data
@Story("Ответ об изменении данных пользователя")
public class Ingredient implements Serializable {
    @JsonProperty("_id")
    private String id;
    private String name;
    private String type;
    private Long proteins;
    private Long fat;
    private Long carbohydrates;
    private Long calories;
    private Long price;
    private String image;
    private String image_mobile;
    private String image_large;
    @JsonProperty("__v")
    private Long v;

}
