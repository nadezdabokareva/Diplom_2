package diplom2.repository.Impl;

import diplom2.entity.Ingredient;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static diplom2.repository.Impl.IngredientRepositoryImpl.ingredientRepository;
import static org.junit.Assert.*;

public class IngredientRepositoryImplTest {

    @Test
    public void getAllIngredients(){
        Optional<List<Ingredient>> ingredients = Optional.of(ingredientRepository.getIngredients());
        assertFalse(ingredients.isEmpty());
    }
}