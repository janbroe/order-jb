package com.switchfully;

import com.switchfully.domain.user.Role;
import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import com.switchfully.service.user.dtos.CreateUserDTO;
import com.switchfully.service.user.dtos.UserDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserRepository customerRepository;

    @BeforeEach
    void createCustomerRepository() {
        customerRepository.save(new User().setFirstname("Frank").setLastname("Verstraeten").setEmail("frankiexxx@gmail.com").setAddress("Beeldekensstraat 5").setPhoneNumber("0455667788").setPassword("pwd").setRole(Role.CUSTOMER));
    }

    @Test
    void addCustomer() {

        CreateUserDTO given = new CreateUserDTO()
                .setFirstname("Brad")
                .setLastname("Pit")
                .setEmail("bp@outlook.com")
                .setAddress("hollywood")
                .setPhoneNumber("6655112233");

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
