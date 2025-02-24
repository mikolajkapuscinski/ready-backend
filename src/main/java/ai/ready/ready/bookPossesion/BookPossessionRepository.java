package ai.ready.ready.bookPossesion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookPossessionRepository extends JpaRepository<BookPossession, Long> {
    Optional<BookPossession> findByBookIdAndUserId(Long bookId, Long userId);
    Integer countAllByUserIdAndState(Long userId, BookState state);
    Integer countAllByBookIdAndState(Long bookId, BookState state);
    void deleteAllByBookIdAndUserId(Long bookId, Long userId);
}
