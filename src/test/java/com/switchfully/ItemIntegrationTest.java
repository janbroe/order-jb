package com.switchfully;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
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

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    void addItemRepository() {
        itemRepository.addItem(new Item("firework", "nice exploding stuff", 34.95, 5));
    }

    @Test
    void addItem() {

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
}

