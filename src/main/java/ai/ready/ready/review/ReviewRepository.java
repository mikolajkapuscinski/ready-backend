package ai.ready.ready.review;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findReviewByBookPossession_User_Id(Long userId, Limit limit);

    List<Review> findReviewsByBookPossession_Id_BookId(Long bookId, Limit limit);
}
