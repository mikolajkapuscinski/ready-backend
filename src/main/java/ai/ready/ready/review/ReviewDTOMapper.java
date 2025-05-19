package ai.ready.ready.review;

import ai.ready.ready.user.User;
import ai.ready.ready.user.dto.UserDTO;
import ai.ready.ready.user.dto.UserDTOMapper;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ReviewDTOMapper {

    @Autowired
    private UserDTOMapper userDTOMapper;

    public List<ReviewDTO> toReviewDTOList(List<Review> reviews, User reviewer) {
        return reviews.stream().map((review -> {
            UserDTO reviewerDTO = userDTOMapper.toUserDTO(reviewer);
            return new ReviewDTO(
                    review.getId(),
                    review.getTitle(),
                    reviewerDTO,
                    review.getContent(),
                    review.getRating().getValue(),
                    review.getCreatedAt()
            );
        })).toList();
    }

}
