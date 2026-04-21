package app.repository;

import app.dto.BookSummaryDTO;
import app.model.Book;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class BookRepository {
    private final EntityManager entityManager;

    public BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Book book) {
        entityManager.persist(book);
    }

    public Book update(Book book) {
        return entityManager.merge(book);
    }

    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }

    public List<Book> findAll() {
        return entityManager
                .createQuery("FROM Book", Book.class)
                .getResultList();
    }

    public void delete(Book book) {
        entityManager.remove(book);
    }

    public List<Book> findByPriceGreaterThan(BigDecimal price) {
        return entityManager.createQuery(
                        "SELECT b FROM Book b WHERE b.price > :price",
                        Book.class
                )
                .setParameter("price", price)
                .getResultList();
    }

    public List<Book> findByTitleContaining(String keyword) {
        return entityManager.createQuery(
                        "SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(:keyword)",
                        Book.class
                )
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }

    public List<Book> findAllOrderedByTitle() {
        return entityManager.createQuery(
                "SELECT b FROM Book b ORDER BY b.title ASC",
                Book.class
        ).getResultList();
    }

    public List<Book> findByAuthorName(String authorName) {
        return entityManager.createQuery(
                        """
                        SELECT b
                        FROM Book b
                        JOIN b.author a
                        WHERE a.name = :name
                        """,
                        Book.class
                )
                .setParameter("name", authorName)
                .getResultList();
    }

    public List<BookSummaryDTO> findBookSummaries() {
        return entityManager.createQuery(
                """
                SELECT NEW BookSummaryDTO(
                    b.title,
                    a.name
                )
                FROM Book b
                JOIN b.author a
                ORDER BY b.title
                """,
                BookSummaryDTO.class
        ).getResultList();
    }

}
