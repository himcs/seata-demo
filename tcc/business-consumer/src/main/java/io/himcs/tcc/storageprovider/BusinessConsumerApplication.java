package io.himcs.tcc.storageprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:provider/*.xml")
public class BusinessConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessConsumerApplication.class, args);
    }
}
