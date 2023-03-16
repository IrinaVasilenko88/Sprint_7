package praktikum.generator;//package ru.yandex.praktikum.generator;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import ru.yandex.praktikum.model.FullOrderRequest;
//
//import java.util.List;
//
//public class OrderRequestGenerator {
//
//    public static FullOrderRequest getRandomOrder() {
//        FullOrderRequest fullOrderRequest = new FullOrderRequest();
//        fullOrderRequest.setFirstName(RandomStringUtils.randomAlphabetic(5));
//        fullOrderRequest.setAddress(RandomStringUtils.randomAlphabetic(5));
//        fullOrderRequest.setColor(List.of(new String[]{"GREY", "BLACK"}));
//        fullOrderRequest.setComment(RandomStringUtils.randomAlphabetic(20));
//        fullOrderRequest.setDeliveryDate("2023-06-06");
//        fullOrderRequest.setLastName(RandomStringUtils.randomAlphabetic(10));
//        fullOrderRequest.setMetroStation(RandomStringUtils.random(2, false, true));
//        fullOrderRequest.setPhone(RandomStringUtils.random(10, false, true));
//        fullOrderRequest.setRentTime(5);
//        return fullOrderRequest;
//    }
//}
