package eureka_server.loan_core_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LoanCoreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanCoreServiceApplication.class, args);
    }
}