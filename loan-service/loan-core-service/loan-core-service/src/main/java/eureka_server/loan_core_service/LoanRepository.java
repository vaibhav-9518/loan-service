package eureka_server.loan_core_service;

import eureka_server.loan_core_service.LoanApplication;
import eureka_server.loan_core_service.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<LoanApplication, Long> {

    // Native query to insert loan
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO loan_application (customer_id, amount, tenure, status) VALUES (:customerId, :amount, :tenure, :status)", nativeQuery = true)
    void insertLoan(@Param("customerId") Long customerId,
                    @Param("amount") Double amount,
                    @Param("tenure") Integer tenure,
                    @Param("status") String status);

    // Native query to find by id
    @Query(value = "SELECT * FROM loan_application WHERE id = :id", nativeQuery = true)
    Optional<LoanApplication> findByIdNative(@Param("id") Long id);

    // Native query to update status
    @Modifying
    @Transactional
    @Query(value = "UPDATE loan_application SET status = :status WHERE id = :id", nativeQuery = true)
    void updateStatusNative(@Param("id") Long id, @Param("status") String status);
}
