package diplom2;

import com.google.gson.JsonObject;
import diplom2.entity.User;
import diplom2.restClients.UserRestClient;
import diplom2.restClients.UserStatusRestClient;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.Assert.*;


public class UserOrderTest {

    @Test
    //500 status
    public void createUser(){
        User user = generateRandomUser();
        ValidatableResponse validatableResponse = UserStatusRestClient.createUser(user);
        assertEquals(200, validatableResponse.extract().statusCode());
    }

    @Test
    //403 status
    public void registrationUser(){
        User user = generateRandomUser();
        ValidatableResponse validatableResponse = UserStatusRestClient.registerUser(user);
        assertEquals(200, validatableResponse.extract().statusCode());
    }
    private User generateRandomUser() {
        return User.builder()
                .password(RandomStringUtils.randomAlphabetic(12))
                .name(RandomStringUtils.randomAlphabetic(6))
                .email(String.format("%s@%s.ru", RandomStringUtils.randomAlphabetic(6), RandomStringUtils.randomAlphabetic(6)))
                .build();
    }
}
