package com.switchfully;

import com.switchfully.domain.user.UserRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class SecurityControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Test
    void login_givenValidEmailAndPassword() {
        String authorization = Base64.getEncoder().encodeToString("admin@order.com:pwd".getBytes());

        RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .headers("Authorization", "Basic " + authorization)
                .get("/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void login_givenInValidEmail() {
        String authorization = Base64.getEncoder().encodeToString("invalid@order.com:pwd".getBytes());

        RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .headers("Authorization", "Basic " + authorization)
                .get("/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void login_givenInValidPassword() {
        String authorization = Base64.getEncoder().encodeToString("admin@order.com:invalidpwd".getBytes());

        RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .headers("Authorization", "Basic " + authorization)
                .get("/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
