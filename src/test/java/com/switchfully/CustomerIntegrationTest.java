package com.switchfully;

import com.switchfully.domain.customer.Customer;
import com.switchfully.domain.customer.CustomerRepository;
import com.switchfully.service.customer.CustomerMapper;
import com.switchfully.service.customer.dtos.CreateCustomerDTO;
import com.switchfully.service.customer.dtos.CustomerDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void createCustomerRepository() {
        customerRepository.createCustomer(new Customer("Frank", "Verstraeten", "frankiexxx@gmail.com", "Beeldekensstraat 5", "0455667788"));
    }

    @Test
    void addCustomer() {

        CreateCustomerDTO given = new CreateCustomerDTO()
                .setFirstname("Brad")
                .setLastname("Pit")
                .setEmail("bp@outlook.com")
                .setAddress("hollywood")
                .setPhoneNumber("6655112233");

        CustomerDTO result = RestAssured
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
                .as(CustomerDTO.class);

        assertThat(result).isNotNull();
        assertThat(result.getFirstname()).isEqualTo(given.getFirstname());
        assertThat(result.getLastname()).isEqualTo(given.getLastname());
        assertThat(result.getEmail()).isEqualTo(given.getEmail());
        assertThat(result.getAddress()).isEqualTo(given.getAddress());
        assertThat(result.getPhoneNumber()).isEqualTo(given.getPhoneNumber());

    }


}
