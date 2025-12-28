package eureka_server.loan_core_service;

import eureka_server.loan_core_service.*;
import eureka_server.loan_core_service.*;
import eureka_server.loan_core_service.*;
import eureka_server.loan_core_service.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LoanService {

    private final LoanRepository repository;

    public LoanService(LoanRepository repository) {
        this.repository = repository;
    }

    public Mono<LoanApplicationDto> createLoan(LoanApplicationDto dto) {
        // Use native query
        repository.insertLoan(
                dto.getCustomerId(),
                dto.getAmount(),
                dto.getTenure(),
                LoanStatus.CREATED.name()
        );

        // Fetch the saved entity to return as DTO
        LoanApplication saved = repository.findByIdNative(dto.getId()).orElse(null);
        if (saved == null) {
            return Mono.empty();
        }

        LoanApplicationDto response = new LoanApplicationDto();
        response.setId(saved.getId());
        response.setCustomerId(saved.getCustomerId());
        response.setAmount(saved.getAmount());
        response.setTenure(saved.getTenureMonths());
        response.setStatus(saved.getStatus());

        return Mono.just(response);
    }

    public Mono<LoanApplicationDto> updateStatus(Long id, LoanStatus status) {
        // Update using native query
        repository.updateStatusNative(id, status.name());

        // Fetch updated entity
        LoanApplication updated = repository.findByIdNative(id).orElse(null);
        if (updated == null) {
            return Mono.empty();
        }

        LoanApplicationDto dto = new LoanApplicationDto();
        dto.setId(updated.getId());
        dto.setCustomerId(updated.getCustomerId());
        dto.setAmount(updated.getAmount());
        dto.setTenure(updated.getTenureMonths());
        dto.setStatus(updated.getStatus());

        return Mono.just(dto);
    }
}