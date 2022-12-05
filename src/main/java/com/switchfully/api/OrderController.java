package com.switchfully.api;

import com.switchfully.service.order.dtos.CreateOrderDTO;
import com.switchfully.service.order.dtos.OrderDTO;
import com.switchfully.service.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces  = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORDER_ITEM')")
    public OrderDTO orderItems(@RequestBody CreateOrderDTO createOrderDTO, Principal principal) {
        log.info("POST -> order item(s)" + createOrderDTO.toString());
        return orderService.orderItems(createOrderDTO, principal.getName());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('GET_ALL_ORDERS')")
    public List<OrderDTO> getAllOrders() {
        log.info("GET -> get all orders");
        return orderService.getAllOrders();
    }
}
