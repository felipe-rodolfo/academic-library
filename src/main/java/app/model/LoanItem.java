package app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "loan_items")
public class LoanItem {

    @EmbeddedId
    private LoanItemId id = new LoanItemId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("loanId")
    private Loan loan;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    private Book book;

    private Integer quantity;

    protected LoanItem() {
    }

    public LoanItem(Loan loan, Book book, Integer quantity) {
        this.loan = loan;
        this.book = book;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }
}