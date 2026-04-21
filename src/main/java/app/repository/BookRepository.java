package app.repository;

import app.dto.BookFilter;
import app.dto.BookSummaryDTO;
import app.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    public List<Book> findAllWithAuthor() {
        return entityManager.createQuery(
                """
                SELECT b
                FROM Book b
                JOIN FETCH b.author
                ORDER BY b.title
                """,
                Book.class
        ).getResultList();
    }

    public List<Book> search(BookFilter filter) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Book> query = cb.createQuery(Book.class);

        Root<Book> book = query.from(Book.class);

        Join<Object, Object> author = book.join("author");

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getTitle() != null) {
            predicates.add(
                    cb.like(
                            cb.lower(book.get("title")),
                            "%" + filter.getTitle().toLowerCase() + "%"
                    )
            );
        }

        if (filter.getMinPrice() != null) {
            predicates.add(
                    cb.greaterThanOrEqualTo(
                            book.get("price"),
                            filter.getMinPrice()
                    )
            );
        }

        if (filter.getYear() != null) {
            predicates.add(
                    cb.equal(book.get("publicationYear"), filter.getYear())
            );
        }

        if (filter.getAuthorName() != null) {
            predicates.add(
                    cb.equal(author.get("name"), filter.getAuthorName())
            );
        }

        query.select(book)
                .where(predicates.toArray(new Predicate[0]))
                .orderBy(cb.asc(book.get("title")));

        return entityManager.createQuery(query).getResultList();
    }

}
