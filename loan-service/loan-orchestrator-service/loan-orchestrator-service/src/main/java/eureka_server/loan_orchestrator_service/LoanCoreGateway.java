package eureka_server.loan_orchestrator_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import eureka_server.loan_contract.dto.*;
import eureka_server.loan_contract.dto.LoanRequestDto;
import eureka_server.loan_contract.*;

@FeignClient(name = "loan-core-service")
public interface LoanCoreGateway {

    @PostMapping("/loans")
    Mono<LoanApplicationDto> createLoan(@RequestBody LoanRequestDto dto);

    @PutMapping("/loans/{id}/status")
    Mono<LoanApplicationDto> updateStatus(@PathVariable Long id, @RequestBody LoanStatus status);
}