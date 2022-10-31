package com.switchfully;

import com.switchfully.domain.exceptions.MultipleItemGroupsWithSameIdInOrderException;
import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.domain.user.Role;
import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import com.switchfully.service.item.dtos.ItemDTO;
import com.switchfully.service.order.dtos.CreateItemGroupDTO;
import com.switchfully.service.order.dtos.CreateOrderDTO;
import com.switchfully.service.order.dtos.OrderDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Base64;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    private final Item newBalloonItem = new Item("balloons", "nice flying stuff", 6.95, 10);
    private final Item newConfettiItem = new Item("confetti", "nice small sticky stuff", 14.95, 10);
    private final String AdminAuthorization = Base64.getEncoder().encodeToString("jth@hotmail.com:pwd".getBytes());
    private final String badAuthorization = Base64.getEncoder().encodeToString("xxx@hotmail.com:xxx".getBytes());

    @BeforeEach
    public void addUsersToUserRepo() {
        userRepository.createUser(new User().setFirstname("Jaak").setLastname("Trekhaak").setEmail("jth@hotmail.com").setAddress("Remorkbaan 66").setPhoneNumber("04999001122").setPassword("pwd").setRole(Role.ADMIN));

        itemRepository.addItem(newBalloonItem);
        itemRepository.addItem(newConfettiItem);

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
                .baseUri("http://localhost")
                .headers("Authorization", "Basic " + AdminAuthorization)
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
                .baseUri("http://localhost")
                .headers("Authorization", "Basic " + AdminAuthorization)
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
                .baseUri("http://localhost")
                .headers("Authorization", "Basic " + badAuthorization)
                .port(port)
                .when()
                .post("/orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
