package brightlyTestQa.api.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
    public RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2")
                .addHeader("Authorization", "Bearer "+ "special-key")
                .addHeader("Content-Type", "application/json")
                .build().log().all();
    }
}
