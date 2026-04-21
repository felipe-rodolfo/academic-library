package app;

import app.dto.BookFilter;
import app.model.Book;
import app.service.BookService;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final EntityManager em;
    private final BookService bookService;

    public ConsoleMenu(EntityManager em) {
        this.em = em;
        this.bookService = new BookService(em);
    }

    public void show() {

        int option;

        do {
            printMenu();
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> createBook();
                case 2 -> listBooks();
                case 3 -> searchBooks();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }

        } while (option != 0);
    }

    private void printMenu() {
        System.out.println();
        System.out.println("=== BIBLIOTECA ===");
        System.out.println("1 - Cadastrar livro");
        System.out.println("2 - Listar livros");
        System.out.println("3 - Buscar livros");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private void createBook() {

        System.out.print("Título: ");
        String title = scanner.nextLine();

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Ano: ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.print("Preço: ");
        BigDecimal price = new BigDecimal(scanner.nextLine());

        Book book = new Book(title, isbn, year, price);

        em.getTransaction().begin();
        bookService.create(book);
        em.getTransaction().commit();

        System.out.println("Livro cadastrado.");
    }

    private void listBooks() {
        bookService.listAll()
                .forEach(book ->
                        System.out.println(
                                book.getId() + " - " + book.getTitle()
                        ));
    }

    private void searchBooks() {

        System.out.print("Título contém: ");
        String title = scanner.nextLine();

        BookFilter filter = new BookFilter();
        filter.setTitle(title);

        bookService.search(filter)
                .forEach(book ->
                        System.out.println(book.getTitle()));
    }
}