package app.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LoanItemId implements Serializable {

    private Long loanId;
    private Long bookId;

    public LoanItemId() {
    }

    public LoanItemId(Long loanId, Long bookId) {
        this.loanId = loanId;
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanItemId that)) return false;
        return Objects.equals(loanId, that.loanId)
                && Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, bookId);
    }
}