package diplom2.restClients;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class IngredientRestClient extends BasicRestClient {
    public static final String INGREDIENTS_PATH = "/api/ingredients/";

    @Step("Регистрация пользователя")
    public static ValidatableResponse getAllIngredients() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(INGREDIENTS_PATH)
                .then();
    }

}
