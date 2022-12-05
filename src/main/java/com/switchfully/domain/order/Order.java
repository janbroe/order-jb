package com.switchfully.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.switchfully.domain.itemGroup.ItemGroup;
import com.switchfully.domain.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
    private Long orderId;
    private double orderPrice;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order() {
    }

    public Order(double orderPrice, User user) {
        this.orderPrice = orderPrice;
        this.user = user;
    }



    private static double getSumOfGroupListsInOrder(List<ItemGroup> itemGroupList) {
        return itemGroupList.stream()
                .map(itemgroup -> itemgroup.getAmount() * itemgroup.getItemPrice())
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public Long getId() {
        return orderId;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public User getUser() {
        return user;
    }
}
