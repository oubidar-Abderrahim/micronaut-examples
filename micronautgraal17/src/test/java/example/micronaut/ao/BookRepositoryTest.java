package example.micronaut.ao;

import io.micronaut.data.repository.CrudRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@MicronautTest
public class BookRepositoryTest {

    @Inject
    AuthorRepository authorRepository;

    @Inject
    BookRepository bookRepository;

    @Test
    void saveBookTest() {
        Author stephanKing = authorRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(stephanKing);
        Book theShining = bookRepository.save(new Book("The Shining",1000,stephanKing));
        Assertions.assertNotNull(theShining);
        Assertions.assertEquals("The Shining", theShining.title());
        Assertions.assertEquals(1000, theShining.pages());
        Assertions.assertEquals(stephanKing, theShining.author());
        Assertions.assertTrue(theShining.enabled());

    }

    @Test
    void saveAllBooksTest() {
        Author stephanKing = authorRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(stephanKing);
        Iterable<Book> books = bookRepository.saveAll(Arrays.asList(
                new Book("The Stand", 1000, stephanKing),
                new Book("The Shining", 500, stephanKing)
        ));
        Assertions.assertNotNull(books);
    }

    @Test
    void saveAllBooksThenFindAllTest() {
        Author stephanKing = authorRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(stephanKing);
        Iterable<Book> savedBooks = bookRepository.saveAll(Arrays.asList(
                new Book("The Stand", 1000, stephanKing),
                new Book("The Shining", 500, stephanKing)
        ));
        Assertions.assertNotNull(savedBooks);
        List<Book> savedBookAsList = new ArrayList<>();
        savedBooks.forEach(savedBookAsList::add);
        List<Book> foundBooks = bookRepository.findAll();
        Assertions.assertNotNull(foundBooks);
        Assertions.assertTrue(foundBooks.containsAll(savedBookAsList));

    }

    @Test
    void saveBookThenFindByIdTest() {
        Author stephanKing = authorRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(stephanKing);
        Book theShining = bookRepository.save(new Book("The Shining",1000,stephanKing));
        Optional<Book> optionalBook = bookRepository.findById(theShining.id());
        Assertions.assertTrue(optionalBook.isPresent());
        Book book = optionalBook.get();
        Assertions.assertEquals(theShining.title(), book.title());
        Assertions.assertEquals(theShining.pages(), book.pages());
        Assertions.assertEquals(theShining.author(), book.author());
        Assertions.assertEquals(stephanKing, book.author());
        Assertions.assertTrue(theShining.enabled());
    }

    @Test
    void findByIdTest() {
        Book book = bookRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(book);
        Assertions.assertEquals(1L,book.id());
    }

    @Test
    void findAllGetFirstTest() {
        Book book = bookRepository.findAll().iterator().next();
        Assertions.assertNotNull(book);
    }

    @Test
    void findAllGetFirstAsEntityTest() {
        Entity<Long> entity = bookRepository.findAll().iterator().next();
        Assertions.assertNotNull(entity);
    }

    @Test
    void findByIDAsEntityTest() {
        Entity<Long> entity = bookRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(entity);
    }

    @Test
    void findAllGetFirstByCrudRepositoryTest() {
        CrudRepository<Book, Long> crudRepository = this.bookRepository;
        Book book = crudRepository.findAll().iterator().next();
        Assertions.assertNotNull(book);
    }

    @Test
    void findByIDUsingCrudRepositoryTest() {
        CrudRepository<Book, Long> crudRepository = this.bookRepository;
        Book book = crudRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(book);

    }
}
