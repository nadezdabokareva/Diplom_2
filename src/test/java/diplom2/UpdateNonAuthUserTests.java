package diplom2;

import diplom2.DTO.resposeDTO.ErrorResponseDTO;
import diplom2.DTO.resposeDTO.ResponseRegisterDTO;
import diplom2.entity.User;
import diplom2.restClients.UserRestClient;
import diplom2.service.UserService;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static diplom2.service.UserService.generateRandomEmail;
import static diplom2.service.UserService.generateRandomName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@Story("Тесты на изменение данных не авторизованного пользователя")
public class UpdateNonAuthUserTests {

    private static User user;
    private static String token;

    @Before
    @DisplayName("Создание случайного пользователя перед тестом")
    public void setUp() {
        user = UserService.generateRandomUser();
        ResponseRegisterDTO responseRegisterDTO = UserRestClient.registerUser(user).extract().as(ResponseRegisterDTO.class);
        token = responseRegisterDTO.getAccessToken();
    }

    @After
    @DisplayName("Удаление данных о пользователе после теста")
    public void tearDown() {
        user.setToken(token);
        UserRestClient.deleteUser(user.getToken());
    }

    @Test
    @DisplayName("Тест на изменение почты не существующего пользователя")
    public void changeEmail() {
        user.setEmail(generateRandomEmail());

        ValidatableResponse validatableResponse = UserRestClient.setUserData(user);
        ErrorResponseDTO errorResponseDTO = validatableResponse.extract().as(ErrorResponseDTO.class);

        assertEquals(401, validatableResponse.extract().statusCode());
        assertFalse(errorResponseDTO.getSuccess());

    }

    @Test
    @DisplayName("Тест на изменение имени не существующего пользователя")
    public void changeName() {
        user.setName(generateRandomName());

        ValidatableResponse validatableResponse = UserRestClient.setUserData(user);
        ErrorResponseDTO errorResponseDTO = validatableResponse.extract().as(ErrorResponseDTO.class);

        assertEquals(401, validatableResponse.extract().statusCode());
        assertFalse(errorResponseDTO.getSuccess());
    }
}
