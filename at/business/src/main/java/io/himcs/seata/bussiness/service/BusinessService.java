package io.himcs.seata.bussiness.service;


import io.himcs.seata.common.dto.OrderDTO;

public interface BusinessService {
    OrderDTO sale(String goodsCode, Integer quantity, String username, Integer points, Integer amount);
}
