package com.liberia.Library.controller;

import com.liberia.Library.model.Loan;
import com.liberia.Library.service.LoanService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public Loan createLoan(@RequestParam Long memberId, @RequestParam Long bookId) throws BadRequestException {
        return loanService.createLoan(memberId, bookId);
    }

    @PutMapping("/{loanId}/return")
    public ResponseEntity<Void> returnLoan(@PathVariable Long loanId) {
        loanService.returnLoan(loanId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long loanId) {
        Optional<Loan> loanOptional = loanService.getLoanById(loanId);
        if (loanOptional.isPresent()) {
            return ResponseEntity.ok(loanOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{loanId}")
    public ResponseEntity<Loan> updateLoan(@PathVariable Long loanId, @RequestBody Loan loan) {
        Loan updatedLoan = loanService.updateLoan(loanId, loan);
        if (updatedLoan != null) {
            return ResponseEntity.ok(updatedLoan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{loanId}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long loanId) {
        loanService.deleteLoan(loanId);
        return ResponseEntity.noContent().build();
    }
}