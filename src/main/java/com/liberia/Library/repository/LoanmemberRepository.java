package com.liberia.Library.repository;

import com.liberia.Library.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanmemberRepository extends JpaRepository<Loan, Long> {
}
