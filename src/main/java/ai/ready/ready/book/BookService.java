package ai.ready.ready.book;

import ai.ready.ready.book.bookPossesion.Review;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookCardDtoMapper bookCardDtoMapper;

    public List<BookCardDto> getBooks(String title, String author) {
        if (title == null && author == null)
            return  bookCardDtoMapper.toBookCardDtoList((List<Book>) bookRepository.findAll());
        if (title == null)
            return bookCardDtoMapper.toBookCardDtoList(bookRepository.findByAuthor(author));
        if (author == null)
            return bookCardDtoMapper.toBookCardDtoList(bookRepository.findByTitle(title));

        return bookCardDtoMapper.toBookCardDtoList(bookRepository.findByTitleAndAuthor(title, author));
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

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

    public List<BookCardDto> getRecentlyFinishedByUserId(Long userId, int limit) {
        return bookCardDtoMapper.toBookCardDtoList(bookRepository.findRecentlyFinishedBooksByUserId(userId, Limit.of(limit)));
    }

    public List<BookCardDto> getToReadByUserId(Long userId, int limit) {
        return bookCardDtoMapper.toBookCardDtoList(bookRepository.findToReadBooksByUserId(userId, Limit.of(limit)));
    }

    public List<Review> getUserReviews(Long userId, int limit) {
        return bookRepository.findReviewsByUserId(userId, Limit.of(limit));
    }
}
