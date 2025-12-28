package eureka_server.loan_orchestrator_service;

import org.springframework.web.bind.annotation.*;

import eureka_server.loan_contract.dto.LoanDecisionResult;
import eureka_server.loan_contract.dto.LoanRequestDto;
import eureka_server.loan_contract.*;
import eureka_server.loan_orchestrator_service.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orchestrator")
public class LoanOrchestratorController {

    private final LoanOrchestratorService orchestratorService;

    public LoanOrchestratorController(LoanOrchestratorService orchestratorService) {
        this.orchestratorService = orchestratorService;
    }

    @PostMapping("/apply")
    public Mono<LoanDecisionResult> applyForLoan(@RequestBody LoanRequestDto dto) {
        return orchestratorService.applyForLoan(dto);
    }
}
