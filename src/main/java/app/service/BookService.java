package app.service;

import app.dto.BookSummaryDTO;
import app.model.Book;
import app.repository.BookRepository;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class BookService {
    private final BookRepository repository;

    public BookService(EntityManager entityManager) {
        this.repository = new BookRepository(entityManager);
    }

    public void create(Book book) {
        repository.save(book);
    }

    public List<Book> listAll() {
        return repository.findAll();
    }

    public void changePrice(Long id, BigDecimal newPrice) {
        Book book = repository.findById(id);

        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }

        book.updatePrice(newPrice);
    }

    public void delete(Long id) {
        Book book = repository.findById(id);

        if (book != null) {
            repository.delete(book);
        }
    }

    public List<Book> expensiveBooks(BigDecimal price) {
        return repository.findByPriceGreaterThan(price);
    }

    public List<Book> searchByTitle(String text) {
        return repository.findByTitleContaining(text);
    }

    public List<BookSummaryDTO> summaries() {
        return repository.findBookSummaries();
    }

    public List<Book> listAllWithAuthor() {
        return repository.findAllWithAuthor();
    }
}
