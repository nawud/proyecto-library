package com.liberia.Library.service;

import com.liberia.Library.model.Book;
import com.liberia.Library.model.Member;
import com.liberia.Library.repository.IMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MemberService {
    private final IMemberRepository iMemberRepository;

    public MemberService(IMemberRepository iMemberRepository) {
        this.iMemberRepository = iMemberRepository;
    }


    public List<Member> getAll() {return iMemberRepository.findAll();}
    public void addMember (Member newMember) {iMemberRepository.save(newMember);}
    public void deleteMember (Long id){iMemberRepository.deleteById(id);}
    public Optional<Member> findMember(Long id) {return iMemberRepository.findById(id);}
    public Member updateMember(Long id, Member updateMember) {
        Optional<Member> foundMember = iMemberRepository.findById(id);
        if (foundMember.isPresent()) {
            Member existingMember = foundMember.get();


            existingMember.setFirstName(updateMember.getFirstName());
            existingMember.setLastName(updateMember.getLastName());
            existingMember.setEmail(updateMember.getEmail());
            existingMember.setPhoneNumber(updateMember.getPhoneNumber());
            existingMember.setId(updateMember.getId());

            return iMemberRepository.save(existingMember);
        }
        throw new RuntimeException("Member not found with id: " + id);
    }
}
