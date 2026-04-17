package app;

import app.model.Author;
import app.model.Book;
import app.repository.BookRepository;
import app.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        Author author = new Author("Robert Martin");

        Book book1 = new Book(
                "Clean Code",
                "1111",
                2008,
                new BigDecimal("90")
        );

        Book book2 = new Book(
                "Clean Architecture",
                "2222",
                2017,
                new BigDecimal("120")
        );

        author.addBook(book1);
        author.addBook(book2);

        em.getTransaction().begin();

        em.persist(author);

        em.getTransaction().commit();
        em.close();
    }
}