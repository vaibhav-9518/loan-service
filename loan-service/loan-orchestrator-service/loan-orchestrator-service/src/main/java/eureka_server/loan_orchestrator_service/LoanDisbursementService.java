package eureka_server.loan_orchestrator_service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanDisbursementService {

    @Transactional
    public void disburse(Long loanId) {

        updateLoanStatus(loanId, "DISBURSED");

        boolean auditFailed = true;
        if (auditFailed) {
            throw new RuntimeException("Audit failed");
        }
    }

    private void updateLoanStatus(Long loanId, String status) {
        // JPA repository save/update
    }
}
