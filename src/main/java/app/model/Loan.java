package app.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate loanDate;

    @OneToMany(
            mappedBy = "loan",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<LoanItem> items = new ArrayList<>();

    protected Loan() {
    }

    public Loan(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public void addItem(Book book, Integer quantity) {
        LoanItem item = new LoanItem(this, book, quantity);
        items.add(item);
    }

    public Long getId() {
        return id;
    }

    public List<LoanItem> getItems() {
        return items;
    }
}