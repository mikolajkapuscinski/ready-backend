package ai.ready.ready.review;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ReviewDTOMapper {
    List<ReviewDTO> toReviewDTOList(List<Review> reviews);
}
