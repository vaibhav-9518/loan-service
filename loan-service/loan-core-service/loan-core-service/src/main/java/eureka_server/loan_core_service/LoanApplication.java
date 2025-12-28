package eureka_server.loan_core_service;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//LoanApplication.java
@Entity
@Table(name = "loan_applications")
public class LoanApplication {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private long customerId;
 private Double amount;
 private Integer tenureMonths;

 @Enumerated(EnumType.STRING)
 private LoanStatus status; // APPLIED, APPROVED, REJECTED, DISBURSED

 public Long getId() {
	return id;
 }

 public void setId(Long id) {
	this.id = id;
 }

 public long getCustomerId() {
	return customerId;
 }

 public void setCustomerId(long customerId) {
	this.customerId = customerId;
 }

 public Double getAmount() {
	return amount;
 }

 public void setAmount(Double amount) {
	this.amount = amount;
 }

 public Integer getTenureMonths() {
	return tenureMonths;
 }

 public void setTenureMonths(Integer tenureMonths) {
	this.tenureMonths = tenureMonths;
 }

 public LoanStatus getStatus() {
	return status;
 }

 public void setStatus(LoanStatus status) {
	this.status = status;
 }

 // getters/setters/constructors
}

