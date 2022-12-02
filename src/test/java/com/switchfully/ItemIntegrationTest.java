package com.switchfully;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.service.item.dtos.CreateItemDTO;
import com.switchfully.service.item.dtos.ItemDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class ItemIntegrationTest {

    public static final String keycloakSecretKey = "A62SnboFANzmoRE3v8ROz1M2bHdkY7rT";
    public static final String keycloakTokenLink = "https://keycloak.switchfully.com/auth/realms/order-jb/protocol/openid-connect/token";
    @LocalServerPort
    private int port;

    @Autowired
    private ItemRepository itemRepository;

    private static String customerToken;
    private static String adminToken;

    @BeforeAll
    static void generateCustomerToken() {
        customerToken = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("username", "customer")
                .formParam("password", "customer")
                .formParam("grant_type", "password")
                .formParam("client_id", "order-jb")
                .formParam("client_secret", keycloakSecretKey)
                .when()
                .post(keycloakTokenLink)
                .then()
                .extract()
                .path("access_token")
                .toString();
    }

    @BeforeAll
    static void generateAdminToken() {
        adminToken = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("username", "admin")
                .formParam("password", "admin")
                .formParam("grant_type", "password")
                .formParam("client_id", "order-jb")
                .formParam("client_secret", keycloakSecretKey)
                .when()
                .post(keycloakTokenLink)
                .then()
                .extract()
                .path("access_token")
                .toString();
    }

    @Test
    void addItemHappyPath() {

        CreateItemDTO given = new CreateItemDTO()
                .setName("Big firework")
                .setDescription("nice exploding stuff with extra boom boom")
                .setPrice(68.99)
                .setAmount(10);

        ItemDTO result = RestAssured
                .given()
                .body(given)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + adminToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(ItemDTO.class);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(given.getName());
        assertThat(result.getDescription()).isEqualTo(given.getDescription());
        assertThat(result.getPrice()).isEqualTo(given.getPrice());
        assertThat(result.getAmount()).isEqualTo(given.getAmount());

    }

    @Test
    void updateItemHappyPath() {

        Item initialItem = itemRepository.findAll().stream()
                .filter(items -> items.getName().equals("firework"))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("firework item not found in item repo"));

        CreateItemDTO given = new CreateItemDTO()
                .setName("Big firework")
                .setDescription("nice exploding stuff with extra boom boom")
                .setPrice(68.99)
                .setAmount(10);

        RestAssured
                .given()
                .body(given)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + adminToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .put("items/" + initialItem.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        Item result = itemRepository.findById(initialItem.getId()).orElse(null);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(given.getName());
        assertThat(result.getDescription()).isEqualTo(given.getDescription());
        assertThat(result.getPrice()).isEqualTo(given.getPrice());
        assertThat(result.getAmount()).isEqualTo(given.getAmount());

    }

    @Test
    void whenUpdateItemWithCustomerCredentials_thenExpectAuthorizationException() {

        Item initialItem = itemRepository.findAll().stream()
                .filter(items -> items.getName().equals("firework"))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("firework item not found in item repo"));

        CreateItemDTO given = new CreateItemDTO()
                .setName("Big firework")
                .setDescription("nice exploding stuff with extra boom boom")
                .setPrice(68.99)
                .setAmount(10);

        RestAssured
                .given()
                .body(given)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + customerToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .put("items/" + initialItem.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());

    }

    @Test
    void whenUpdateItemWithWrongItemId_thenExpectNoSushElementException() {

        CreateItemDTO given = new CreateItemDTO()
                .setName("Big firework")
                .setDescription("nice exploding stuff with extra boom boom")
                .setPrice(68.99)
                .setAmount(10);

        RestAssured
                .given()
                .body(given)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + adminToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .put("items/" + "wrongItemId")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }
}

