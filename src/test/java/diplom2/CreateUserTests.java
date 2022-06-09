package diplom2;

import diplom2.DTO.resposeDTO.ErrorResponseDTO;
import diplom2.DTO.resposeDTO.ResponseCreateUserDTO;
import diplom2.entity.User;
import diplom2.restClients.UserRestClient;
import diplom2.service.UserService;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

@Story("Тесты на создание пользователя")
public class CreateUserTests {

    private static User user;

    @Before
    @DisplayName("Создание случайного пользователя перед тестом")
    public void setUp() {
        user = UserService.generateRandomUser();
    }

    @After
    @DisplayName("Удаление данных о пользователе после теста")
    public void tearDown() {
        UserRestClient.deleteUser(user.getToken());
    }

    @Test
    @DisplayName("Тест на создание заказа нового пользователя")
    public void createUniqueUser() {
        ValidatableResponse validatableResponse = UserRestClient.createUser(user);
        ResponseCreateUserDTO responseCreateUserDTO = validatableResponse.extract().as(ResponseCreateUserDTO.class);
        user.setToken(responseCreateUserDTO.getAccessToken());

        assertEquals(200, validatableResponse.extract().statusCode());
        assertTrue(responseCreateUserDTO.getSuccess());
        assertEquals(user.getEmail(), responseCreateUserDTO.getUser().getEmail());
        assertEquals(user.getName(), responseCreateUserDTO.getUser().getName());
    }

    @Test
    @DisplayName("Тест на создание заказа пользователя с уже существующими данными")
    public void createNonUniqueUser() {
        ValidatableResponse correctResponse = UserRestClient.createUser(user);
        user.setToken(correctResponse.extract().as(ResponseCreateUserDTO.class).getAccessToken());

        ValidatableResponse validatableResponse = UserRestClient.createUser(user);
        ErrorResponseDTO responseDTO = validatableResponse.extract().as(ErrorResponseDTO.class);

        assertEquals(403, validatableResponse.extract().statusCode());
        assertEquals("User already exists", responseDTO.getMessage());
        assertFalse(responseDTO.getSuccess());
    }

    @Test
    @DisplayName("Тест на создание заказа нового пользователя без обязательного поля")
    public void createUserWithoutField() {
        String name = user.getName();
        user.setName(null);

        ValidatableResponse validatableResponse = UserRestClient.createUser(user);
        ErrorResponseDTO responseDTO = validatableResponse.extract().as(ErrorResponseDTO.class);

        user.setName(name);

        assertEquals(403, validatableResponse.extract().statusCode());
        assertEquals("Email, password and name are required fields", responseDTO.getMessage());
        assertFalse(responseDTO.getSuccess());
    }
}
