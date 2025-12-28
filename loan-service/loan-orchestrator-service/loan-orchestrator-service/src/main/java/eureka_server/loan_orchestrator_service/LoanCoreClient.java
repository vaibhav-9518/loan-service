package eureka_server.loan_orchestrator_service;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import eureka_server.loan_contract.dto.LoanApplicationDto;
import eureka_server.loan_contract.dto.LoanRequestDto;
import eureka_server.loan_contract.*;

@Component
public class LoanCoreClient {

    private final WebClient webClient;

    public LoanCoreClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<LoanApplicationDto> create(LoanRequestDto dto) {
        return webClient.post()
                .uri("http://loan-core-service/loans")
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(LoanApplicationDto.class);
    }

    public Mono<LoanApplicationDto> updateStatus(Long id, eureka_server.loan_contract.dto.LoanStatus status) {
        return webClient.put()
                .uri("http://loan-core-service/loans/{id}/status", id)
                .bodyValue(status)
                .retrieve()
                .bodyToMono(LoanApplicationDto.class);
    }
}