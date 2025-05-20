package ai.ready.ready.review;

import ai.ready.ready.book.Book;
import ai.ready.ready.book.dto.BookCardDto;
import ai.ready.ready.book.dto.BookCardDtoMapper;
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

    @Autowired
    private BookCardDtoMapper bookCardDtoMapper;

    public List<ReviewDTO> toReviewDTOList(List<Review> reviews) {
        return reviews.stream().map((review -> {
            User reviewer = review.getBookPossession().getUser();
            Book book = review.getBookPossession().getBook();
            UserDTO reviewerDTO = userDTOMapper.toUserDTO(reviewer);
            BookCardDto bookCardDto = bookCardDtoMapper.toBookCardDto(book);
            return new ReviewDTO(
                    review.getId(),
                    review.getTitle(),
                    bookCardDto,
                    reviewerDTO,
                    review.getContent(),
                    review.getRating().getValue(),
                    review.getCreatedAt()
            );
        })).toList();
    }

}
