package io.himcs.seata.bussiness.controller;

import io.himcs.seata.bussiness.service.BusinessService;
import io.himcs.seata.common.dto.OrderDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BusinessController {

    @Resource
    BusinessService businessService;

    @RequestMapping("/test1")
    public OrderDTO test1() {
        return businessService.sale("cola", 100, "zhangsan", 100, 200);
    }
    @RequestMapping("/test2")
    public OrderDTO test2() {
        return businessService.sale("cola", 10000, "zhangsan", 100, 200000);
    }

}
