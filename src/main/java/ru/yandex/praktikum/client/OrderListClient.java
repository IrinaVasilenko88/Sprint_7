package praktikum.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderListClient extends RestClient {

    @Step("Send GET Request to /api/v1/orders - get list of Orders")
    public ValidatableResponse createOrder() {
        return given()
                .spec(getBaseRequestSpec())
                .get("orders")
                .then();
    }
}
