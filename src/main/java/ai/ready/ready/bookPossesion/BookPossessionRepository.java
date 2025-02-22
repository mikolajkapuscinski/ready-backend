package ai.ready.ready.bookPossesion;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookPossessionRepository extends CrudRepository<BookPossession, Long> {
    Optional<BookPossession> findByBookIdAndUserId(Long bookId, Long userId);
    Integer countAllByUserIdAndState(Long userId, BookState state);
    Integer countAllByBookIdAndState(Long bookId, BookState state);
    void deleteAllByBookIdAndUserId(Long bookId, Long userId);
}
