package eureka_server.loan_orchestrator_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import reactor.core.publisher.Mono;

@FeignClient(name = "credit-score-service")
public interface CreditScoreGateway {

    @GetMapping("/score/{customerId}")
    Mono<Integer> fetchScore(@PathVariable("customerId") Long customerId);
}
