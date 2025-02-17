package ai.ready.ready.bookPossesion;

import ai.ready.ready.book.BookCardDto;
import ai.ready.ready.book.BookCardDtoMapper;
import ai.ready.ready.book.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookPossessionService {
    private final BookRepository bookRepository;
    private final BookCardDtoMapper bookCardDtoMapper;

    public Integer getNumberOfFinishedBooks(Long userId) {
        return bookRepository.findNumberOfFinishedBooksByUserId(userId);
    }


    public Integer getNumberOfFinishedPages(Long userId) {
        Integer numberOfFinishedPages = bookRepository.findFinishedPagesByUserId(userId);
        if (numberOfFinishedPages == null)
            return 0;
        return numberOfFinishedPages;
    }

    public List<BookCardDto> getCurrentlyReadingByUserId(Long userId, int limit) {
        return bookCardDtoMapper.toBookCardDtoList(bookRepository.findCurrentlyReadingBooksByUserId(userId, Limit.of(limit)));
    }

    public List<BookCardDto> getCurrentlyReadingByUserId(Long userId) {
        return bookCardDtoMapper.toBookCardDtoList(bookRepository.findCurrentlyReadingBooksByUserId(userId, Limit.unlimited()));
    }

    public List<BookCardDto> getRecentlyFinishedByUserId(Long userId, int limit) {
        return bookCardDtoMapper.toBookCardDtoList(bookRepository.findRecentlyFinishedBooksByUserId(userId, Limit.of(limit)));
    }

    public List<BookCardDto> getRecentlyFinishedByUserId(Long userId) {
        return bookCardDtoMapper.toBookCardDtoList(bookRepository.findRecentlyFinishedBooksByUserId(userId, Limit.unlimited()));
    }

    public List<BookCardDto> getToReadByUserId(Long userId, int limit) {
        return bookCardDtoMapper.toBookCardDtoList(bookRepository.findToReadBooksByUserId(userId, Limit.of(limit)));
    }

    public List<BookCardDto> getToReadByUserId(Long userId) {
        return bookCardDtoMapper.toBookCardDtoList(bookRepository.findToReadBooksByUserId(userId, Limit.unlimited()));
    }

    public List<Review> getUserReviews(Long userId, int limit) {
        return bookRepository.findReviewsByUserId(userId, Limit.of(limit));
    }
}
