package ru.yandex.praktikum.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.yandex.praktikum.config.Config;

public class RestClient {
    public RequestSpecification getBaseRequestSpec() {
        return new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(Config.getBaseUri()).build();
    }
}
