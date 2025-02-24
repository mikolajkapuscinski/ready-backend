package ai.ready.ready.book;

import ai.ready.ready.book.dto.BookCardDto;
import ai.ready.ready.book.dto.BookCardDtoMapper;
import ai.ready.ready.book.dto.BookDTO;
import ai.ready.ready.book.dto.SearchRequest;
import ai.ready.ready.bookPossesion.BookPossessionDTO;
import ai.ready.ready.bookPossesion.BookPossessionDTOMapper;
import ai.ready.ready.bookPossesion.BookPossessionService;
import ai.ready.ready.review.ReviewDTO;
import ai.ready.ready.review.ReviewDTOMapper;
import ai.ready.ready.exceptions.BookNotFoundException;
import ai.ready.ready.review.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<BookCardDto> getBooks(SearchRequest searchCriteria, Pageable pageable) {
        if (searchCriteria == null) {
            return bookRepository.findAll(pageable).map(bookCardDtoMapper::toBookCardDto);
        }
        Specification<Book> spec = Specification.where(null);
        spec = switch (searchCriteria.category()) {
            case "title" -> spec.and(BookSpecs.containsTitle(searchCriteria.value()));
            case "author" -> spec.and(BookSpecs.containsAuthor(searchCriteria.value()));
            case "isbn" -> spec.and(BookSpecs.containsIsbn(searchCriteria.value()));
            default -> spec.and(BookSpecs.containsIsbn(searchCriteria.value()).or(BookSpecs.containsTitle(searchCriteria.value()).or(BookSpecs.containsAuthor(searchCriteria.value()))));
        };

        return bookRepository.findAll(spec, pageable).map(bookCardDtoMapper::toBookCardDto);
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
