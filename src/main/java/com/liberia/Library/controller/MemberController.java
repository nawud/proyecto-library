package com.liberia.Library.controller;

import com.liberia.Library.model.Member;
import com.liberia.Library.repository.IMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private IMemberRepository memberRepository;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            return ResponseEntity.ok(memberOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member member) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            Member existingMember = memberOptional.get();
            existingMember.setFirstName(member.getFirstName());
            existingMember.setLastName(member.getLastName());
            existingMember.setEmail(member.getEmail());
            existingMember.setPhoneNumber(member.getPhoneNumber());
            existingMember.setMembershipDate(member.getMembershipDate());
            existingMember.setStatus(member.getStatus());
            memberRepository.save(existingMember);
            return ResponseEntity.ok(existingMember);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Member> searchMembers(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        if (firstName != null && lastName != null) {
            return memberRepository.findByFirstNameAndLastName(firstName, lastName);
        } else if (firstName != null) {
            return memberRepository.findByFirstName(firstName);
        } else if (lastName != null) {
            return memberRepository.findByLastName(lastName);
        } else {
            return memberRepository.findAll();
        }
    }
}