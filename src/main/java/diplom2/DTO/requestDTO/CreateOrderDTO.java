package diplom2.DTO.requestDTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import diplom2.entity.Ingredient;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class CreateOrderDTO implements Serializable {

    private class IngredientDTO {
        String _id;

        public IngredientDTO(Ingredient ingredient) {
            this._id = ingredient.get_id();
        }
    }

    private IngredientDTO[] ingredients;

    public CreateOrderDTO(List ingredientList) {
        List<IngredientDTO> list = new ArrayList<>();
        for (Object ingredient : ingredientList) {
            IngredientDTO ingredientDTO = new IngredientDTO((Ingredient) ingredient);
            list.add(ingredientDTO);
        }
        this.ingredients = (IngredientDTO[]) list.toArray();
    }
}
