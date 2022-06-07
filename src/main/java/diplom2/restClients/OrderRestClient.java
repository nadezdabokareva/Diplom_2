package diplom2.restClients;

import diplom2.DTO.requestDTO.CreateOrderDTO;
import diplom2.entity.Order;
import diplom2.entity.User;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderRestClient extends BasicRestClient {
    public static final String ORDERS_PATH = "/api/orders";

    public static ValidatableResponse createOrder(CreateOrderDTO createOrderDTO) {
        return given()
                .spec(getBaseSpec())
                .and()
                .body(createOrderDTO, ObjectMapperType.GSON)
                .when()
                .post(ORDERS_PATH)
                .then();
    }
}
