package example.micronaut.ao;

public sealed interface Entity<ID>
        permits Author, Book {
    ID id();
}