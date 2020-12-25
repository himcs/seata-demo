package io.himcs.tcc.storageprovider.controller;

import io.himcs.tcc.storageprovider.service.BusinessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
public class TestContoller {
    @Resource
    private BusinessService businessService;

    @GetMapping("/test1")
    public String test1() {
        String uuid = UUID.randomUUID().toString();
        businessService.sale(uuid, "coca", 1, 2);
        return "SUCCESS";
    }

    @GetMapping("/test2")
    public String test2() {
        String uuid = UUID.randomUUID().toString();
        businessService.sale(uuid, "coca", 10000, 20000);
        return "SUCCESS";
    }

    @GetMapping("/test3")
    public String test3() {
        String uuid = UUID.randomUUID().toString();
        businessService.sale(uuid, "coca", 100, 20000);
        return "SUCCESS";
    }
}
