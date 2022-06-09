package diplom2;

import diplom2.DTO.resposeDTO.ErrorResponseDTO;
import diplom2.DTO.resposeDTO.ResponseGetOrders;
import diplom2.DTO.resposeDTO.ResponseRegisterDTO;
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

import static org.junit.Assert.*;

@Story("Тесты на получение заказов пользователя")
public class GetOrderUserTest {

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
    @DisplayName("Тест на получение заказов авторизованного пользователя")
    public void getOrderAuthUser() {
        user.setToken(responseRegisterDTO.getAccessToken());

        ValidatableResponse validatableResponse = OrderRestClient.getAllOrder(user);
        ResponseGetOrders responseGetOrders = validatableResponse.extract().as(ResponseGetOrders.class);

        assertEquals(200, validatableResponse.extract().statusCode());
        assertNotNull(responseGetOrders.getOrders());
        assertTrue(responseGetOrders.getSuccess());
    }

    @Test
    @DisplayName("Тест на получение заказов не авторизованного пользователя")
    public void getOrderNonAuthUser() {

        ValidatableResponse validatableResponse = OrderRestClient.getAllOrder(user);
        ErrorResponseDTO errorResponseDTO = validatableResponse.extract().as(ErrorResponseDTO.class);

        user.setToken(responseRegisterDTO.getAccessToken());

        assertEquals(401, validatableResponse.extract().statusCode());
        assertEquals("You should be authorised", errorResponseDTO.getMessage());
        assertFalse(errorResponseDTO.getSuccess());
    }
}
