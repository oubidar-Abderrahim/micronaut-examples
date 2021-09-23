package example.micronaut.ao;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.H2)
public interface BookRepository extends CrudRepository<Book, Long> {

    @Join(value = "author", type = Join.Type.FETCH)
    List<Book> findAll();

    @Join(value = "author", type = Join.Type.FETCH)
    Optional<Book> findById(@NotNull Long id);
}