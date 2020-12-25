package io.himcs.tcc.storageprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author mcs
 */
@SpringBootApplication
@EnableJpaRepositories
@ImportResource("classpath:provider/*.xml")
public class StorageProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageProviderApplication.class, args);
    }
}
