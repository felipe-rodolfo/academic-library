package app;

import app.dto.BookFilter;
import app.model.*;
import app.repository.BookRepository;
import app.service.BookService;
import app.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        Reader reader = new Reader(
                "Ana",
                "ana@email.com",
                "MAT123"
        );

        Admin admin = new Admin(
                "Carlos",
                "carlos@email.com",
                10
        );

        em.persist(reader);
        em.persist(admin);

        em.getTransaction().commit();
        em.close();
    }
}