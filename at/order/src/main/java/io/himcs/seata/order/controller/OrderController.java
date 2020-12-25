package io.himcs.seata.order.controller;

import io.himcs.seata.common.dto.OrderDTO;
import io.himcs.seata.order.entiry.Order;
import io.himcs.seata.order.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class OrderController {

    @Resource
    private OrderRepository orderRepository;

    @PostMapping("/create")
    Order create(@RequestBody OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        Order save = orderRepository.save(order);
        return save;
    }
}
