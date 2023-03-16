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
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static ru.yandex.praktikum.generator.CourierRequestGenerator.*;

public class CreateNewCourierTest {

    private Courier courier;
    private Integer courierId;

    @Before
    public void setUp() {
        courier = new Courier();
    }

    @After
    public void clearData() {
        if (courierId != null) {
            courier.delete(courierId)
                    .assertThat()
                    .body("ok", equalTo(true));
        }
    }

    @Test
    @DisplayName("Valid data courier creation - Создание нового курьера")
    public void courierCanBeCreatedTest() {
        CourierRequest courierRequest = getRandomCourier();
        courier.create(courierRequest).assertThat().statusCode(SC_CREATED).and().assertThat().body("ok", is(true));
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
    @DisplayName("Same data courier creation - Создание дубликата курьера")
    public void sameDataCourierCreationTest() {
        CourierRequest courierRequest = getRandomCourier();
        courier.create(courierRequest).assertThat().statusCode(SC_CREATED).and().assertThat().body("ok", is(true));
        LoginRequest loginRequest = LoginRequestGenerator.from(courierRequest);
        courierId = courier.login(loginRequest)
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("id", notNullValue())
                .extract()
                .path("id");
        courier.create(courierRequest).assertThat().statusCode(SC_CONFLICT);
    }


    @Test
    @DisplayName("Courier creation without firstName- Создание курьера без имени")
    public void courierWithoutFirstNameCreationTest() {
        CourierRequest courierRequest = getCourierWithoutFirstName();
        courier.create(courierRequest).assertThat().statusCode(SC_CREATED).and().assertThat().body("ok", is(true));
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
    @DisplayName("Courier creation without login - Создание курьера без логина")
    public void courierWithoutLoginCreationTest() {
        CourierRequest courierRequest = getCourierWithoutLogin();
        courier.create(courierRequest).assertThat().statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Courier creation without password - Создание курьера без пароля")
    public void courierWithoutPasswordCreationTest() {
        CourierRequest courierRequest = getCourierWithoutPassword();
        courier.create(courierRequest).assertThat().statusCode(SC_BAD_REQUEST);
    }
}

