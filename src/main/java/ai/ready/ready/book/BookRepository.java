package ai.ready.ready.book;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByTitleAndAuthor(String title, String author);

    @Query("Select SUM(b.numberOfPages) from Book b join BookPossession bp on bp.book.id = b.id where bp.user.id = :userId and bp.state='FINISHED'")
    Integer findTotalPagesByUserId(@Param("userId") Long userId);

    @Query("Select COUNT(*) from Book b join BookPossession bp on bp.book.id = b.id where bp.user.id = :userId and bp.state='FINISHED'")
    Integer findTotalBooksByUserId(@Param("userId") Long userId);
}
