package com.liberia.Library.repository;

import com.liberia.Library.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IMemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByFirstNameAndLastName(String firstName, String lastName);

    List<Member> findByFirstName(String firstName);

    List<Member> findByLastName(String lastName);

}

