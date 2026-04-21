package app;

import app.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class LibraryApplication {

    public void start() {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            ConsoleMenu menu = new ConsoleMenu(em);
            menu.show();
        } finally {
            em.close();
        }
    }
}