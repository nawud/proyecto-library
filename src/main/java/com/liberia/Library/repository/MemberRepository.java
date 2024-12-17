package com.liberia.Library.repository;

import com.liberia.Library.model.Member;
import com.liberia.Library.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

