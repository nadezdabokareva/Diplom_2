package diplom2;

import diplom2.DTO.resposeDTO.ResponseRegisterDTO;
import diplom2.DTO.resposeDTO.ResponseUpdateUserDTO;
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
import static org.junit.Assert.assertTrue;

@Story("Тесты на изменение данных авторизованного пользователя")
public class UpdateAuthUserTests {

    private static User user;

    @Before
    @DisplayName("Создание случайного пользователя перед тестом")
    public void setUp() {
        user = UserService.generateRandomUser();
        ResponseRegisterDTO responseRegisterDTO = UserRestClient.registerUser(user).extract().as(ResponseRegisterDTO.class);
        user.setToken(responseRegisterDTO.getAccessToken());
    }

    @After
    @DisplayName("Удаление данных о пользователе после теста")
    public void tearDown() {
        UserRestClient.deleteUser(user.getToken());
    }

    @Test
    @DisplayName("Тест на изменение почты пользователя")
    public void changeEmail() {
        user.setEmail(generateRandomEmail());

        ValidatableResponse validatableResponse = UserRestClient.setUserData(user);
        ResponseUpdateUserDTO responseUpdateUserDTO = validatableResponse.extract().as(ResponseUpdateUserDTO.class);

        assertEquals(200, validatableResponse.extract().statusCode());
        assertTrue(responseUpdateUserDTO.getSuccess());
        assertEquals(user.getEmail(), responseUpdateUserDTO.getUser().getEmail());

    }

    @Test
    @DisplayName("Тест на изменение имени пользователя")
    public void changeName() {
        user.setName(generateRandomName());

        ValidatableResponse validatableResponse = UserRestClient.setUserData(user);
        ResponseUpdateUserDTO responseUpdateUserDTO = validatableResponse.extract().as(ResponseUpdateUserDTO.class);

        assertEquals(200, validatableResponse.extract().statusCode());
        assertTrue(responseUpdateUserDTO.getSuccess());
        assertEquals(user.getName(), responseUpdateUserDTO.getUser().getName());
    }
}
