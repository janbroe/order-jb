package com.switchfully;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.domain.user.Role;
import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import com.switchfully.service.item.dtos.CreateItemDTO;
import com.switchfully.service.item.dtos.ItemDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Base64;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void addItemRepository() {
        itemRepository.addItem(new Item("firework", "nice exploding stuff", 34.95, 5));
    }

    @Test
    void addItemHappyPath() {

        String authorization = Base64.getEncoder().encodeToString("jth@hotmail.com:pwd".getBytes());
        userRepository.createUser(new User().setFirstname("Jaak").setLastname("Trekhaak").setEmail("jth@hotmail.com").setAddress("Remorkbaan 66").setPhoneNumber("04999001122").setPassword("pwd").setRole(Role.ADMIN));

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
                .baseUri("http://localhost")
                .headers("Authorization", "Basic " + authorization)
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

        String authorization = Base64.getEncoder().encodeToString("jth@hotmail.com:pwd".getBytes());
        userRepository.createUser(new User().setFirstname("Jaak").setLastname("Trekhaak").setEmail("jth@hotmail.com").setAddress("Remorkbaan 66").setPhoneNumber("04999001122").setPassword("pwd").setRole(Role.ADMIN));

        Item initialItem = itemRepository.getAllItems().stream()
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
                .baseUri("http://localhost")
                .headers("Authorization", "Basic " + authorization)
                .port(port)
                .when()
                .put("items?itemId=" + initialItem.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        Item result = itemRepository.getItemById(initialItem.getId());

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(given.getName());
        assertThat(result.getDescription()).isEqualTo(given.getDescription());
        assertThat(result.getPrice()).isEqualTo(given.getPrice());
        assertThat(result.getAmount()).isEqualTo(given.getAmount());

    }

    @Test
    void whenUpdateItemWithWrongCredentials_thenExpectAuthorizationException() {

        String authorization = Base64.getEncoder().encodeToString("notAdmin@hotmail.com:pwd".getBytes());
        userRepository.createUser(new User().setFirstname("Jos").setLastname("Vos").setEmail("notAdmin@hotmail.com").setAddress("vosbaan 3").setPhoneNumber("04999334455").setPassword("pwd").setRole(Role.CUSTOMER));

        Item initialItem = itemRepository.getAllItems().stream()
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
                .baseUri("http://localhost")
                .headers("Authorization", "Basic " + authorization)
                .port(port)
                .when()
                .put("items?itemId=" + initialItem.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());

    }

    @Test
    void whenUpdateItemWithWrongItemId_thenExpectNoSushElementException() {

        String authorization = Base64.getEncoder().encodeToString("jth@hotmail.com:pwd".getBytes());
        userRepository.createUser(new User().setFirstname("Jaak").setLastname("Trekhaak").setEmail("jth@hotmail.com").setAddress("Remorkbaan 66").setPhoneNumber("04999001122").setPassword("pwd").setRole(Role.ADMIN));

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
                .baseUri("http://localhost")
                .headers("Authorization", "Basic " + authorization)
                .port(port)
                .when()
                .put("items?itemId=" + "wrongItemId")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }
}

