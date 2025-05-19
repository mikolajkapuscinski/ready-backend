package ai.ready.ready.review;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;


    public List<Review> getUserReviews(Long userId, int limit) {
        return reviewRepository.findReviewByBookPossession_User_Id(userId, Limit.of(limit));
    }

    public List<Review> getBookReviews(Long bookId, int limit) {
        return reviewRepository.findReviewsByBookPossession_Id_BookId(bookId, Limit.of(limit));
    }

    public Integer calculateBookAvgRating(List<ReviewDTO> reviews) {
        if (reviews == null || reviews.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (ReviewDTO review : reviews) {
            sum += review.rating();
        }
        return sum / reviews.size();
    }
}
