package com.switchfully.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @OneToMany(mappedBy = "order")
    private List<ItemGroup> itemGroupList;
    private double orderPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Order() {
    }

    public Order(List<ItemGroup> itemGroupList) {
        this.itemGroupList = itemGroupList;
        this.orderPrice = getSumOfGroupListsInOrder(itemGroupList);
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

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public User getUser() {
        return user;
    }
}
