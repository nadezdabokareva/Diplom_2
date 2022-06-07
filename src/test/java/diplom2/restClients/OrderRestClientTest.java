package diplom2.restClients;

import diplom2.DTO.requestDTO.CreateOrderDTO;
import diplom2.entity.Ingredient;
import org.junit.Test;

import java.util.List;

import static diplom2.repository.Impl.IngredientRepositoryImpl.ingredientRepository;

public class OrderRestClientTest {

    @Test
    public void createTestOrder() {
        OrderRestClient.createOrder(new CreateOrderDTO(List.of(ingredientRepository.getIngredients().get(0),
                (ingredientRepository.getIngredients().get(1)))));
    }


}