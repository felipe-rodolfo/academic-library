package app;

import app.model.Author;
import app.model.Book;
import app.model.Category;
import app.repository.BookRepository;
import app.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        Author author = new Author("Robert Martin");

        Book book = new Book(
                "Clean Code",
                "3333",
                2008,
                new BigDecimal("99.90")
        );

        Category c1 = new Category("Programming");
        Category c2 = new Category("Architecture");
        Category c3 = new Category("Backend");

        book.addCategory(c1);
        book.addCategory(c2);
        book.addCategory(c3);

        author.addBook(book);

        em.getTransaction().begin();

        em.persist(author);

        em.getTransaction().commit();
        em.close();
    }
}