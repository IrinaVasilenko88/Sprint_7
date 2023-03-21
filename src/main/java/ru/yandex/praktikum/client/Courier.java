package ru.yandex.praktikum.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.model.CourierRequest;
import ru.yandex.praktikum.model.LoginRequest;

import static io.restassured.RestAssured.given;

public class Courier extends RestClient {

    @Step("Send POST Request to /api/v1/courier - create Courier")
    public ValidatableResponse createCourier(CourierRequest courierRequest) {
        return given()
                .spec(getBaseRequestSpec())
                .body(courierRequest)
                .post("courier")
                .then();
    }

    @Step("Send POST Request to /api/v1/courier/login - login as Courier")
    public ValidatableResponse login(LoginRequest loginRequest) {
        return given()
                .spec(getBaseRequestSpec())
                .body(loginRequest)
                .post("courier/login")
                .then();
    }

    @Step("Send DELETE Request to /api/v1/courier/id - delete Courier")
    public ValidatableResponse delete(int id) {
        return given()
                .spec(getBaseRequestSpec())
                .delete(String.format("courier/%d", id))
                .then();
    }
}
