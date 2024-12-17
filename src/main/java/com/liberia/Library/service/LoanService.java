package com.liberia.Library.service;

import com.liberia.Library.model.Book;
import com.liberia.Library.model.Loan;
import com.liberia.Library.model.Member;
import com.liberia.Library.repository.IBookRepository;
import com.liberia.Library.repository.LoanmemberRepository;
import com.liberia.Library.repository.MemberRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanmemberRepository loanRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private IBookRepository bookRepository;

    public Loan createLoan(Long memberId, Long BookId) throws BadRequestException {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new ChangeSetPersister.NotFoundException("Miembro no encontrado"));
        Book book = bookRepository.findById(BookId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException("Libro no encontrado"));

        if (book.isAvailable()) {
            Loan loan = new Loan();
            loan.setMember(member);
            loan.setBook(book);
            loan.setLoanDate(LocalDate.now());
            loan.setDueDate(LocalDate.now().plusDays(14)); // 14 días de préstamo por defecto
            book.setAvailable(false);
            return loanRepository.save(loan);
        } else {
            throw new BadRequestException("El libro no está disponible");
        }
    }

    public void returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException("Préstamo no encontrado"));

        // Calcular multa si la devolución es tardía
        if (LocalDate.now().isAfter(loan.getDueDate())) {
            // ... Lógica para calcular y aplicar la multa ...
        }

        loan.setReturnedDate(LocalDate.now());
        loan.getBook().setAvailable(true);
        loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return List.of();
    }


    public Optional<Loan> getLoanById(Long loanId) {
    }

    public Loan updateLoan(Long loanId, Loan loan) {
    }
}