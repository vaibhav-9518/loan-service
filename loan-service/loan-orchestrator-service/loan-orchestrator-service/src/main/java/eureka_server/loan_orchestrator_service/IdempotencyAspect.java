package eureka_server.loan_orchestrator_service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import eureka_server.loan_contract.dto.LoanRequestDto;

@Aspect
@Component
public class IdempotencyAspect {

    private final Map<String, Object> cache = new ConcurrentHashMap<>();

    @Around("@annotation(eureka_server.loan_orchestrator_service.idempotency.Idempotent) && args(dto,..)")
    public Object handle(ProceedingJoinPoint pjp, LoanRequestDto dto)
            throws Throwable {

        return cache.computeIfAbsent(
                dto.getRequestId(),   // requestId MUST be unique
                key -> {
                    try {
                        return pjp.proceed();
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}
