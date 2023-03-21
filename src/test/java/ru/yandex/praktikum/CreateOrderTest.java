package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.client.OrderClient;
import ru.yandex.praktikum.model.FullOrderRequest;

import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final List<String> color;

    int track;

    public CreateOrderTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Создание заказа. Тестовые данные {0},{1},{2},{3}")
    public static Object[][] getTestData() {
        return new Object[][]{
                new List[]{List.of("GREY")},
                new List[]{List.of("BLACK")},
                new List[]{List.of("BLACK, GREY")},
                new List[]{List.of("")}
        };
    }

    @Test
    @DisplayName("Full order creation - создание заказа")
    public void createOrderTest() {
        FullOrderRequest fullOrderRequest = new FullOrderRequest("Ирина", "Василенко", "Проспект Мира д.6 кв.43", "4", "+7 915 345 45 45", 7, "2023-03-16", "Нет домофона", color);
        OrderClient orderClient = new OrderClient();
        track = orderClient.createOrder(fullOrderRequest)
                .assertThat().statusCode(SC_CREATED)
                .and().body("track", notNullValue())
                .extract().path("track");
    }
}
