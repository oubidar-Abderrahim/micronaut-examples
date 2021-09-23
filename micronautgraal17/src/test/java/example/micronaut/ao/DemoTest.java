package example.micronaut.ao;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
public class DemoTest {

    @Inject
    AuthorRepository authorRepository;

    @Inject
    BookRepository bookRepository;

    @Test
    public void testItSaves() {
        Assertions.assertEquals(1, authorRepository.count());
    }

    @Test
    public void testTheClientForAuthor(BookStoreApi.BookClient client) {
        Author author = authorRepository.findAll().iterator().next();
        Assertions.assertEquals("Stephan King",author.name());
        String result = client.describe("author", author.id());
        Assertions.assertEquals(author.name(),result);
    }

    @Test
    public void testTheClientForNonExistingType(BookStoreApi.BookClient client) {
        String result = client.describe("dragon", 1L);
        Assertions.assertEquals(null,result);
    }

    @Test
    public void testTheClientForBook(BookStoreApi.BookClient client) {
        Book book = bookRepository.findAll().iterator().next();
        Assertions.assertEquals("The Stand",book.title());
        String result = client.describe("book", book.id());
        Assertions.assertEquals(book.title(),result);
    }

    @Client("/")
    interface TestBookStoreClient extends BookStoreApi.BookClient {}
}
