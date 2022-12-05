package com.switchfully;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.service.itemGroup.dtos.CreateItemGroupDTO;
import com.switchfully.service.order.dtos.CreateOrderDTO;
import com.switchfully.service.order.dtos.OrderDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class OrderIntegrationTest {

    public static final String keycloakSecretKey = "A62SnboFANzmoRE3v8ROz1M2bHdkY7rT";
    public static final String keycloakTokenLink = "https://keycloak.switchfully.com/auth/realms/order-jb/protocol/openid-connect/token";
    @LocalServerPort
    private int port;

    @Autowired
    private ItemRepository itemRepository;

    private final Item newBalloonItem = new Item("balloons", "nice flying stuff", 6.95, 10);
    private final Item newConfettiItem = new Item("confetti", "nice small sticky stuff", 14.95, 10);

    @BeforeEach
    public void addUsersToUserRepo() {
        itemRepository.save(newBalloonItem);
        itemRepository.save(newConfettiItem);
    }

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
    void happyPathPlaceOrder() {

        CreateOrderDTO given = new CreateOrderDTO().setCreateItemGroupDTOList(List.of(
                new CreateItemGroupDTO()
                        .setSelectedItemId(newBalloonItem.getId())
                        .setAmount(5),
                new CreateItemGroupDTO()
                        .setSelectedItemId(newConfettiItem.getId())
                        .setAmount(5)
        ));

        OrderDTO result = RestAssured
                .given()
                .body(given)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + adminToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .post("/orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(OrderDTO.class);
    }

    @Test
    void whenplaceOrderWithSameGroupItemIdInOrder_thenExpectBadRequestStatus() {

        CreateOrderDTO given = new CreateOrderDTO().setCreateItemGroupDTOList(List.of(
                new CreateItemGroupDTO()
                        .setSelectedItemId(newBalloonItem.getId())
                        .setAmount(5),
                new CreateItemGroupDTO()
                        .setSelectedItemId(newBalloonItem.getId())
                        .setAmount(5)
        ));

        RestAssured
                .given()
                .body(given)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + adminToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .post("/orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void placeOrderWithWrongCredentials() {

        CreateOrderDTO given = new CreateOrderDTO().setCreateItemGroupDTOList(List.of(
                new CreateItemGroupDTO()
                        .setSelectedItemId(newBalloonItem.getId())
                        .setAmount(5),
                new CreateItemGroupDTO()
                        .setSelectedItemId(newConfettiItem.getId())
                        .setAmount(5)
        ));

        RestAssured
                .given()
                .body(given)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + "thisIsABadToken")
                .baseUri("http://localhost")
                .port(port)
                .when()
                .post("/orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }
}
