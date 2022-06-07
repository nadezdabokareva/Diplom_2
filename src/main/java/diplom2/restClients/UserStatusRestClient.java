package diplom2.restClients;

import diplom2.entity.User;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserStatusRestClient extends BasicRestClient {
    public static final String REGISTER_PATH = "/api/auth/register";
    public static final String LOGIN_PATH = "/api/auth/login";
    public static final String LOGOUT_PATH = "/api/auth/logout";
    public static final String TOKEN_PATH = "/api/auth/token";

    @Step("Создание пользователя")
    public static ValidatableResponse createUser(User user) {
        return given()
                .spec(getBaseSpec())
                .and().body(user)
                .when()
                .post(REGISTER_PATH)
                .then();
    }
    @Step("Регистрация пользователя")
    public static ValidatableResponse registerUser(User user) {
        return given()
                .spec(getBaseSpec())
                .and().body(user)
                .when()
                .post(REGISTER_PATH)
                .then();
    }
}
