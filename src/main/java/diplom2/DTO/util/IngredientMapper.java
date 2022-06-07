package diplom2.DTO.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import diplom2.entity.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

public class IngredientMapper {
    public static List<Ingredient> mapperListIngredient(List list) {
        ObjectMapper objectMapper = new ObjectMapper();
        return (List) list.stream().map(o -> objectMapper.convertValue(o, Ingredient.class)).collect(Collectors.toList());
    }
}
