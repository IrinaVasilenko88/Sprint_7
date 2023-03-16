package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.client.OrderListClient;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersListTest {

    private OrderListClient orderListClient;

    @Before
    public void setUp() {
        orderListClient = new OrderListClient();
    }

    @Test
    @DisplayName("Get orders list - получить список заказов")
    public void getOrdersListTest() {
        orderListClient.createOrder().assertThat().statusCode(SC_OK)
                .body("orders", notNullValue());
    }
}
