package ru.yandex.praktikum.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.model.FullOrderRequest;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestClient {

    @Step("Send POST Request to /api/v1/orders - create Order")
    public ValidatableResponse createOrder(FullOrderRequest fullOrderRequest) {
        return given()
                .spec(getBaseRequestSpec())
                .body(fullOrderRequest)
                .post("orders")
                .then();
    }
}
