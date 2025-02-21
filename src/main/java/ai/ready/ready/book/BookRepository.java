package ai.ready.ready.book;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByTitleAndAuthor(String title, String author);

    @Query("Select SUM(b.numberOfPages) from Book b join BookPossession bp on bp.book.id = b.id where bp.user.id = :userId and bp.state='FINISHED'")
    Integer findFinishedPagesByUserId(@Param("userId") Long userId);

    @Query("Select COUNT(*) from Book b join BookPossession bp on bp.book.id = b.id where bp.user.id = :userId and bp.state='FINISHED'")
    Integer findNumberOfFinishedBooksByUserId(@Param("userId") Long userId);

    @Query("Select b from Book b join BookPossession bp on bp.book.id = b.id where bp.user.id = :userId and bp.state='IN_PROGRESS'")
    List<Book> findCurrentlyReadingBooksByUserId(@Param("userId") Long userId, Limit limit);

    @Query("Select b from Book b join BookPossession bp on bp.book.id = b.id where bp.user.id = :userId and bp.state='FINISHED'")
    List<Book> findRecentlyFinishedBooksByUserId(@Param("userId") Long userId, Limit limit);

    @Query("Select b from Book b join BookPossession bp on bp.book.id = b.id where bp.user.id = :userId and bp.state='NOT_STARTED'")
    List<Book> findToReadBooksByUserId(@Param("userId") Long userId, Limit limit);

    @Query("Select count(b) from Book b join BookPossession bp on bp.book.id = b.id where bp.book.id = :bookId and bp.state='NOT_STARTED'")
    Integer findNumberOfUsersToReadBook(@Param("bookId") Long bookId);

    @Query("Select count(b) from Book b join BookPossession bp on bp.book.id = b.id where bp.book.id = :bookId and bp.state='IN_PROGRESS'")
    Integer findNumberOfUsersReadingBook(@Param("bookId") Long bookId);

    @Query("Select count(b) from Book b join BookPossession bp on bp.book.id = b.id where bp.book.id = :bookId and bp.state='FINISHED'")
    Integer findNumberOfUsersReadBook(@Param("bookId") Long bookId);
}
