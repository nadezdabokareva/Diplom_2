package diplom2;

import diplom2.DTO.requestDTO.CreateOrderDTO;
import diplom2.DTO.resposeDTO.ErrorResponseDTO;
import diplom2.DTO.resposeDTO.ResponseOrderDTO;
import diplom2.DTO.resposeDTO.ResponseRegisterDTO;
import diplom2.entity.Ingredient;
import diplom2.entity.User;
import diplom2.restClients.OrderRestClient;
import diplom2.restClients.UserRestClient;
import diplom2.service.UserService;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static diplom2.repository.IngredientRepository.ingredientRepository;
import static org.junit.Assert.*;

@Story("Тесты на создание заказа")
public class CreateOrderTests {

    private static User user;
    private static ResponseRegisterDTO responseRegisterDTO;

    @Before
    @DisplayName("Создание случайного пользователя перед тестом")
    public void setUp() {
        user = UserService.generateRandomUser();
        responseRegisterDTO = UserRestClient.registerUser(user).extract().as(ResponseRegisterDTO.class);
    }

    @After
    @DisplayName("Удаление данных о пользователе после теста")
    public void tearDown() {
        UserRestClient.deleteUser(user.getToken());
    }

    @Test
    @DisplayName("Тест на создание заказа с авторизованным пользователем")
    public void createOrderWithAuthorization() {
        user.setToken(responseRegisterDTO.getAccessToken());
        List<Ingredient> listIngredients = ingredientRepository.getIngredients();
        CreateOrderDTO orderDTO = new CreateOrderDTO(listIngredients.get(0), listIngredients.get(1));

        ValidatableResponse validatableResponse = OrderRestClient.createOrder(orderDTO, user);
        ResponseOrderDTO responseOrderDTO = validatableResponse.extract().as(ResponseOrderDTO.class);

        assertEquals(200, validatableResponse.extract().statusCode());
        assertNotNull(responseOrderDTO.getOrder());
        assertNotNull(responseOrderDTO.getName());
        assertTrue(responseOrderDTO.getSuccess());
    }

    @Test
    @DisplayName("Тест на создание заказа с не авторизованным пользователем")
    public void createOrderWithoutAuthorization() {
        List<Ingredient> listIngredients = ingredientRepository.getIngredients();
        CreateOrderDTO orderDTO = new CreateOrderDTO(listIngredients.get(0), listIngredients.get(1));

        ValidatableResponse validatableResponse = OrderRestClient.createOrder(orderDTO, user);
        ResponseOrderDTO responseOrderDTO = validatableResponse.extract().as(ResponseOrderDTO.class);

        user.setToken(responseRegisterDTO.getAccessToken());

        assertEquals(200, validatableResponse.extract().statusCode());
        assertNotNull(responseOrderDTO.getOrder());
        assertNotNull(responseOrderDTO.getName());
        assertTrue(responseOrderDTO.getSuccess());
        assertNull(responseOrderDTO.getOrder().getOwner());
    }

    @Test
    @DisplayName("Тест на создание заказа с ингридиентами")
    public void createOrderWithIngredients() {
        user.setToken(responseRegisterDTO.getAccessToken());
        List<Ingredient> listIngredients = ingredientRepository.getIngredients();
        CreateOrderDTO orderDTO = new CreateOrderDTO(listIngredients.get(0), listIngredients.get(1));

        ValidatableResponse validatableResponse = OrderRestClient.createOrder(orderDTO, user);
        ResponseOrderDTO responseOrderDTO = validatableResponse.extract().as(ResponseOrderDTO.class);

        assertEquals(200, validatableResponse.extract().statusCode());
        assertNotNull(responseOrderDTO.getOrder());
        assertNotNull(responseOrderDTO.getOrder().getIngredients());
        assertNotNull(responseOrderDTO.getName());
        assertTrue(responseOrderDTO.getSuccess());
    }

    @Test
    @DisplayName("Тест на создание заказа с без ингридиентов")
    public void createOrderWithoutIngredients() {
        user.setToken(responseRegisterDTO.getAccessToken());
        CreateOrderDTO orderDTO = new CreateOrderDTO();

        ValidatableResponse validatableResponse = OrderRestClient.createOrder(orderDTO, user);
        ErrorResponseDTO responseOrderDTO = validatableResponse.extract().as(ErrorResponseDTO.class);

        assertEquals(400, validatableResponse.extract().statusCode());
        assertFalse(responseOrderDTO.getSuccess());
        assertEquals("Ingredient ids must be provided", responseOrderDTO.getMessage());
    }

    @Test
    @DisplayName("Тест на создание заказа с некорректным хешем")
    public void createOrderWithoutIncorrectIngredientsHash() {
        user.setToken(responseRegisterDTO.getAccessToken());
        List<Ingredient> listIngredients = ingredientRepository.getIngredients();
        Ingredient ingredient = new Ingredient();
        ingredient.setId("61c0c5a71d1f82001bdaaa6d1");

        CreateOrderDTO orderDTO = new CreateOrderDTO(listIngredients.get(0), ingredient);
        ValidatableResponse validatableResponse = OrderRestClient.createOrder(orderDTO, user);

        assertEquals(500, validatableResponse.extract().statusCode());
    }

}
