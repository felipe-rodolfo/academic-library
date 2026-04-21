package app;

import app.model.Author;
import app.model.Book;
import app.model.Category;
import app.repository.BookRepository;
import app.service.BookService;
import app.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        BookService service = new BookService(em);

        service.summaries()
                .forEach(System.out::println);

        em.close();
    }
}