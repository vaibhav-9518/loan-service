package eureka_server.loan_orchestrator_service;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CreditScoreClient {

    private final WebClient webClient;

    public CreditScoreClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Integer> score(Long customerId) {
        return webClient.get()
                .uri("http://credit-score-service/score/{id}", customerId)
                .retrieve()
                .bodyToMono(Integer.class);
    }
}

