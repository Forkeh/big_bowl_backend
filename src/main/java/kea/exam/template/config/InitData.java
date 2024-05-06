package kea.exam.template.config;

import kea.exam.template.book.Book;
import kea.exam.template.book.BookRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitData implements ApplicationRunner {

    private final BookRepository bookRepository;

    public InitData(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Initializing data...");

        init();
    }

    private void init() {
        System.out.println("Creating items in database...");

        List<Book> books = List.of(
                new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925),
                new Book("To Kill a Mockingbird", "Harper Lee", 1960),
                new Book("1984", "George Orwell", 1949),
                new Book("Pride and Prejudice", "Jane Austen", 1813),
                new Book("The Catcher in the Rye", "J.D. Salinger", 1951),
                new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954),
                new Book("Animal Farm", "George Orwell", 1945),
                new Book("The Hobbit", "J.R.R. Tolkien", 1937),
                new Book("The Little Prince", "Antoine de Saint-Exupéry", 1943),
                new Book("The Da Vinci Code", "Dan Brown", 2003)
        );
        bookRepository.saveAll(books);
    }
}
