package eureka_server.loan_orchestrator_service;

import org.springframework.stereotype.Service;

import eureka_server.loan_contract.dto.LoanApplicationDto;
import eureka_server.loan_contract.dto.LoanDecisionResult;
import eureka_server.loan_contract.dto.LoanRequestDto;
import eureka_server.loan_contract.dto.LoanStatus;
import reactor.core.publisher.Mono;

@Service
public class LoanOrchestratorService {

    private final LoanCoreGateway coreGateway;
    private final CreditScoreGateway creditGateway;

    public LoanOrchestratorService(LoanCoreGateway coreGateway, CreditScoreGateway creditGateway) {
        this.coreGateway = coreGateway;
        this.creditGateway = creditGateway;
    }

    public Mono<LoanDecisionResult> applyForLoan(LoanRequestDto dto) {
        return coreGateway.createLoan(dto)
                .flatMap(saved ->
                        creditGateway.fetchScore(saved.getCustomerId())
                                .flatMap(score -> decide(saved, score)));
    }

    private Mono<LoanDecisionResult> decide(LoanApplicationDto loan, int score) {
        if (score < 600) {
            return coreGateway.updateStatus(loan.getId(), LoanStatus.REJECTED)
                    .thenReturn(new LoanDecisionResult.Rejected("Low credit score " + score));
        }
        return coreGateway.updateStatus(loan.getId(), LoanStatus.APPROVED)
                .flatMap(updated -> coreGateway.updateStatus(updated.getId(), LoanStatus.DISBURSED))
                .thenReturn(new LoanDecisionResult.Approved(loan.getId(), score));
    }
}