package diplom2.restClients;

import diplom2.DTO.requestDTO.CreateOrderDTO;
import diplom2.entity.User;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

@Story("Создание заказа пользователем")
public class OrderRestClient extends BasicRestClient {
    public static final String ORDERS_PATH = "/api/orders";

    @DisplayName("Создание нового заказа")
    public static ValidatableResponse createOrder(CreateOrderDTO createOrderDTO, User user) {
        return given()
                .spec(getBaseSpec())
                .header(new Header("Authorization", user.getToken()))
                .and()
                .body(createOrderDTO)
                .when()
                .post(ORDERS_PATH)
                .then();
    }

    @DisplayName("Получение всех заказов")
    public static ValidatableResponse getAllOrder(User user) {
        return given()
                .spec(getBaseSpec())
                .header(new Header("Authorization", user.getToken()))
                .and()
                .when()
                .get(ORDERS_PATH)
                .then();
    }
}
