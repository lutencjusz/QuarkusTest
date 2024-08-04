package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testAccountGetAllAccounts() {
        given()
                .when()
                .get("/accounts")
                .then()
                .statusCode(200)
                .body(containsString("Alice"));
    }

    @Test
    void testAccountGetAccount() {
        given()
                .when()
                .get("/accounts/Bob")
                .then()
                .statusCode(200)
                .body(containsString("Bob"));
    }

}