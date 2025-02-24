package ai.ready.ready.book;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Query("Select SUM(b.numberOfPages) from Book b join BookPossession bp on bp.book.id = b.id where bp.user.id = :userId and bp.state='FINISHED'")
    Integer findFinishedPagesByUserId(@Param("userId") Long userId);

    @Query("Select b from Book b join BookPossession bp on bp.book.id = b.id where bp.user.id = :userId")
    List<Book> findUsersBook(@Param("userId") Long userId);

    @Query("Select b from Book b join BookPossession bp on bp.book.id = b.id where bp.user.id = :userId and bp.state='IN_PROGRESS'")
    List<Book> findCurrentlyReadingBooksByUserId(@Param("userId") Long userId, Limit limit);

    @Query("Select b from Book b join BookPossession bp on bp.book.id = b.id where bp.user.id = :userId and bp.state='FINISHED'")
    List<Book> findRecentlyFinishedBooksByUserId(@Param("userId") Long userId, Limit limit);

    @Query("Select b from Book b join BookPossession bp on bp.book.id = b.id where bp.user.id = :userId and bp.state='NOT_STARTED'")
    List<Book> findToReadBooksByUserId(@Param("userId") Long userId, Limit limit);

}
