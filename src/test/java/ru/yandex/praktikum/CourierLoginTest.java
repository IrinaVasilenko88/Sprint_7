package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.client.Courier;
import ru.yandex.praktikum.generator.LoginRequestGenerator;
import ru.yandex.praktikum.model.CourierRequest;
import ru.yandex.praktikum.model.LoginRequest;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static ru.yandex.praktikum.generator.CourierRequestGenerator.getRandomCourier;

public class CourierLoginTest {
    private Courier courier;
    private Integer courierId;
    private CourierRequest courierRequest;

    @Before
    public void setUp() {
        courier = new Courier();
        courierRequest = getRandomCourier();
        courier.createCourier(courierRequest).assertThat().statusCode(SC_CREATED)
                .and().body("ok", equalTo(true));
    }

    @After
    public void tearDown() {
        LoginRequest loginRequest1 = LoginRequestGenerator.from(courierRequest);
        courierId = courier.login(loginRequest1)
                .extract()
                .path("id");
        if (courierId != null) {
            courier.delete(courierId)
                    .assertThat()
                    .body("ok", equalTo(true));
        }
    }

    @Test
    @DisplayName("Courier authorization - авторизация курьера")
    public void courierAuthTest() {
        LoginRequest loginRequest = LoginRequestGenerator.from(courierRequest);
        courierId = courier.login(loginRequest)
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("id", notNullValue())
                .extract()
                .path("id");
    }

    @Test
    @DisplayName("Courier authorization without login- авторизация курьера без логина")
    public void courierAuthWithoutLoginTest() {
        LoginRequest loginRequest = LoginRequestGenerator.withoutLogin(courierRequest);
        courierId = courier.login(loginRequest)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .extract()
                .path("id");
    }


    @Test
    @DisplayName("Courier authorization without password - авторизация курьера без пароля")
    public void authorizationWithoutPassword() {
        LoginRequest loginRequest = LoginRequestGenerator.withoutPassword(courierRequest);
        courierId = courier.login(loginRequest)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .extract()
                .path("id");
    }

    @Test
    @DisplayName("Login non-existent courier - авторизация несуществующего курьера")
    public void LoginNonExistentCourier() {
        LoginRequest loginRequest = LoginRequestGenerator.from(courierRequest);
        courierId = courier.login(loginRequest)
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("id", notNullValue())
                .extract()
                .path("id");
        courier.delete(courierId);
        courier.login(loginRequest)
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
