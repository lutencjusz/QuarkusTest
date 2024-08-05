package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testAccountGetAllAccounts() {
        JsonPath response = RestAssured.given()
                .when()
                .get("/accounts")
                .then()
                .statusCode(200)
                .body(notNullValue(),
                        containsString("Alice"),
                        containsString("Bob"))
                .body("size()", greaterThanOrEqualTo(2))
                .extract().body().jsonPath();
        List<Account> accounts = response.getList("", Account.class);
        Account account = accounts.stream()
                .filter(i -> i.name().equals("Alice"))
                .findFirst()
                .orElse(null);
        System.out.println(account);
        MatcherAssert.assertThat("Sprawdzenie nazwy konta", response.getString("name"), containsString("Alice"));
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response.getString("name")).as("Sprawdzenie nazwy projektu").containsOnlyOnce("Alice");
        softAssertions.assertThat(response.getString("name")).as("Sprawdzenie nazwy projektu").containsOnlyOnce("Bob");
        softAssertions.assertThat(response.getString("name")).as("Sprawdzenie, czy nazwa jest pusta").isNotBlank();
        softAssertions.assertThat(response.getString("name")).as("Sprawdzenie, czy nazwa jest pusta").isNotEmpty();
        softAssertions.assertAll();
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