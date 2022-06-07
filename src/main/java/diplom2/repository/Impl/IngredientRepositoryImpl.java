package diplom2.repository.Impl;

import diplom2.repository.IngredientRepository;
import diplom2.entity.Ingredient;
import diplom2.restClients.IngredientRestClient;

import java.util.List;

import static diplom2.DTO.util.IngredientMapper.mapperListIngredient;

public class IngredientRepositoryImpl implements IngredientRepository {

    public final static IngredientRepositoryImpl ingredientRepository = new IngredientRepositoryImpl();

    @Override
    public List<Ingredient> getIngredients() {
        return mapperListIngredient((List) IngredientRestClient.getAllIngredients().extract().body().jsonPath().get("data"));
    }

}
