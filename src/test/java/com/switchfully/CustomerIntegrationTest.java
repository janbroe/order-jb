package com.switchfully;

import com.switchfully.service.user.dtos.CreateUserDTO;
import com.switchfully.service.user.dtos.UserDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CustomerIntegrationTest {

    @LocalServerPort
    private int port;

    @Test
    void addCustomerHappyPath() {

        CreateUserDTO given = new CreateUserDTO()
                .setFirstname("testBrad")
                .setLastname("Pit")
                .setEmail("bp@outlook.com")
                .setAddress("hollywood")
                .setPhoneNumber("6655112233")
                .setUserName("bradPit")
                .setPassword("pwd");

        UserDTO result = RestAssured
                .given()
                .body(given)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(UserDTO.class);

        assertThat(result).isNotNull();
        assertThat(result.getFirstname()).isEqualTo(given.getFirstname());
        assertThat(result.getLastname()).isEqualTo(given.getLastname());
        assertThat(result.getEmail()).isEqualTo(given.getEmail());
        assertThat(result.getAddress()).isEqualTo(given.getAddress());
        assertThat(result.getPhoneNumber()).isEqualTo(given.getPhoneNumber());

    }
}
