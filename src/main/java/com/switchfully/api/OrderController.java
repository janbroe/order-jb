package com.switchfully.api;

import com.switchfully.service.order.dtos.CreateOrderDTO;
import com.switchfully.service.order.dtos.OrderDTO;
import com.switchfully.domain.user.Feature;
import com.switchfully.service.order.OrderService;
import com.switchfully.service.security.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final SecurityService securityService;

    private final OrderService orderService;

    public OrderController(SecurityService securityService, OrderService orderService) {
        this.securityService = securityService;
        this.orderService = orderService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces  = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO orderItems(@RequestHeader String authorization, @RequestBody CreateOrderDTO createOrderDTO) {
        securityService.validateAuthorization(authorization, Feature.ORDER_ITEM);
        log.info("POST -> order item(s)" + createOrderDTO.toString());
        return orderService.orderItems(createOrderDTO);
    }
}
