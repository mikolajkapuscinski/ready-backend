package ai.ready.ready.review;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("Select r from Review r join BookPossession bp on bp.review.id = r.id where bp.user.id = :userId")
    List<Review> findReviewsByUserId(@Param("userId") Long userId, Limit limit);

    @Query("Select r from Review r join BookPossession bp on bp.review.id = r.id where bp.book.id = :bookId")
    List<Review> findReviewsByBookId(@Param("bookId") Long bookId, Limit limit);
}
