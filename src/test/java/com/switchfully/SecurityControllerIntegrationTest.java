package com.switchfully;

import com.switchfully.domain.user.Role;
import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;
    private final User user = new User().setFirstname("Jaak").setLastname("Trekhaak").setEmail("jth@hotmail.com").setAddress("Remorkbaan 66").setPhoneNumber("04999001122").setPassword("pwd").setRole(Role.ADMIN);

    @BeforeEach
    void clearRepositoryAndAddMember() {
        userRepository.createUser(user);
    }

    @Test
    void login_givenValidEmailAndPassword() {
        String authorization = Base64.getEncoder().encodeToString("jth@hotmail.com:pwd".getBytes());

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
        String authorization = Base64.getEncoder().encodeToString("invalid@hotmail.com:pwd".getBytes());

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
        String authorization = Base64.getEncoder().encodeToString("jth@hotmail.com:invalidpwd".getBytes());

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
