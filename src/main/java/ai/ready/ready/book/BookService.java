package ai.ready.ready.book;

import ai.ready.ready.bookPossesion.BookPossessionDTO;
import ai.ready.ready.bookPossesion.BookPossessionDTOMapper;
import ai.ready.ready.bookPossesion.BookPossessionService;
import ai.ready.ready.review.ReviewDTO;
import ai.ready.ready.review.ReviewDTOMapper;
import ai.ready.ready.exceptions.BookNotFoundException;
import ai.ready.ready.review.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookCardDtoMapper bookCardDtoMapper;
    private final ReviewDTOMapper reviewDTOMapper;
    private final ReviewService reviewService;
    private final BookPossessionService bookPossessionService;
    private final BookPossessionDTOMapper bookPossessionDTOMapper;

    public List<BookCardDto> getBooks(String title, String author) {
        if (title == null && author == null)
            return  bookCardDtoMapper.toBookCardDtos((List<Book>) bookRepository.findAll());
        if (title == null)
            return bookCardDtoMapper.toBookCardDtos(bookRepository.findByAuthor(author));
        if (author == null)
            return bookCardDtoMapper.toBookCardDtos(bookRepository.findByTitle(title));

        return bookCardDtoMapper.toBookCardDtos(bookRepository.findByTitleAndAuthor(title, author));
    }

    public BookDTO getBookById(Long id, Long userId) {
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        List<ReviewDTO> reviews = reviewDTOMapper.toReviewDTOList(reviewService.getBookReviews(book.getId(), 5));
        Integer avgRating = reviewService.calculateBookAvgRating(reviews);
        Integer numberOfToReads = bookPossessionService.getNumberOfUsersToReadBook(book.getId());
        Integer numberOfCurrentlyReading = bookPossessionService.getNumberOfUsersReadingBook(book.getId());
        Integer numberOfRead = bookPossessionService.getNumberOfUsersReadBook(book.getId());
        BookPossessionDTO bookPossession = bookPossessionDTOMapper.toDTO(bookPossessionService.getBookPossession(book.getId(), userId));
        return new BookDTO(
            book.getId(),
            book.getTitle(),
            book.getAuthor(),
            book.getIsbn13(),
            book.getIsbn10(),
            book.getCoverUrl(),
            book.getLanguage(),
            book.getNumberOfPages(),
            book.getDescription(),
            book.getDateOfPublication(),
            reviews,
            avgRating,
            numberOfToReads,
            numberOfCurrentlyReading,
            numberOfRead,
            bookPossession
        );
    }
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }


}
