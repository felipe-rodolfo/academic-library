package app;

import app.model.Book;
import app.repository.BookRepository;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();
        BookRepository repository = new BookRepository(em);

        Book book = new Book(
                "Clean Code",
                "9780132350884",
                2008,
                new BigDecimal("99.90")
        );

        em.getTransaction().begin();

        repository.save(book);

        em.getTransaction().commit();

        em.close();

        System.out.println("Livro salvo com sucesso.");
    }
}