package app.repository;

import app.model.Book;
import jakarta.persistence.EntityManager;
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
}
