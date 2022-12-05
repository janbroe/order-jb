package com.switchfully.domain.itemGroup;

import com.switchfully.domain.order.Order;
import com.switchfully.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemGroupRepository extends JpaRepository<ItemGroup, Long> {
    List<ItemGroup> findItemGroupsByOrder(Order order);
}
