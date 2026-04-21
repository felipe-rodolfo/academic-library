package app;

import app.dto.BookFilter;
import app.model.*;
import app.repository.BookRepository;
import app.service.BookService;
import app.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        Book book1 = em.find(Book.class, 1L);
        Book book2 = em.find(Book.class, 2L);

        Loan loan = new Loan(LocalDate.now());

        loan.addItem(book1, 1);
        loan.addItem(book2, 2);

        em.getTransaction().begin();

        em.persist(loan);

        em.getTransaction().commit();
        em.close();
    }
}