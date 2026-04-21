package app;

import app.dto.BookFilter;
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

        BookFilter filter = new BookFilter();
        filter.setTitle("clean");
        filter.setMinPrice(new BigDecimal("80"));
        filter.setAuthorName("Robert Martin");

        service.search(filter)
                .forEach(book -> System.out.println(book.getTitle()));

        em.close();
    }
}