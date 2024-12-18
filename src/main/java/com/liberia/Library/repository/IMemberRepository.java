package com.liberia.Library.repository;
import com.liberia.Library.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IMemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByFirstNameAndLastName(String firstName, String lastName);
    List<Member> findByFirstName(String firstName);
    List<Member> findByLastName(String lastName);
}
