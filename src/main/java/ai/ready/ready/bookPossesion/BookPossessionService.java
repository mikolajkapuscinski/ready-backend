package ai.ready.ready.bookPossesion;

import ai.ready.ready.book.*;
import ai.ready.ready.book.dto.BookCardDto;
import ai.ready.ready.book.dto.BookCardDtoMapper;
import ai.ready.ready.exceptions.BookNotFoundException;
import ai.ready.ready.exceptions.UnknownBookStatusException;
import ai.ready.ready.exceptions.UserNotFoundException;
import ai.ready.ready.user.User;
import ai.ready.ready.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static ai.ready.ready.bookPossesion.BookState.*;

@Service
@AllArgsConstructor
public class BookPossessionService {
    private final BookRepository bookRepository;
    private final BookPossessionRepository bookPossessionRepository;
    private final BookCardDtoMapper bookCardDtoMapper;
    private final BookPossessionDTOMapper bookPossessionDTOMapper;
    private final UserRepository userRepository;

    public BookPossession getBookPossession(Long bookId, Long userId) {
        return bookPossessionRepository.findByBookIdAndUserId(bookId, userId).orElse(null);
    }

    public Integer getNumberOfFinishedBooks(Long userId) {
        return bookPossessionRepository.countAllByUserIdAndState(userId, FINISHED);
    }

    public Integer getNumberOfFinishedPages(Long userId) {
        return bookRepository.findFinishedPagesByUserId(userId).orElse(0);
    }

    public List<BookCardDto> getUsersBook(Long userId) {
        return bookCardDtoMapper.toBookCardDtos(bookRepository.findUsersBook(userId));
    }

    public List<BookCardDto> getCurrentlyReadingByUserId(Long userId, int limit) {
        return bookCardDtoMapper.toBookCardDtos(bookRepository.findCurrentlyReadingBooksByUserId(userId, Limit.of(limit)));
    }

    public List<BookCardDto> getCurrentlyReadingByUserId(Long userId) {
        return bookCardDtoMapper.toBookCardDtos(bookRepository.findCurrentlyReadingBooksByUserId(userId, Limit.unlimited()));
    }

    public List<BookCardDto> getRecentlyFinishedByUserId(Long userId, int limit) {
        return bookCardDtoMapper.toBookCardDtos(bookRepository.findRecentlyFinishedBooksByUserId(userId, Limit.of(limit)));
    }

    public List<BookCardDto> getRecentlyFinishedByUserId(Long userId) {
        return bookCardDtoMapper.toBookCardDtos(bookRepository.findRecentlyFinishedBooksByUserId(userId, Limit.unlimited()));
    }

    public List<BookCardDto> getToReadByUserId(Long userId, int limit) {
        return bookCardDtoMapper.toBookCardDtos(bookRepository.findToReadBooksByUserId(userId, Limit.of(limit)));
    }

    public List<BookCardDto> getToReadByUserId(Long userId) {
        return bookCardDtoMapper.toBookCardDtos(bookRepository.findToReadBooksByUserId(userId, Limit.unlimited()));
    }

    public Integer getNumberOfUsersToReadBook(Long bookId) {
        return bookPossessionRepository.countAllByBookIdAndState(bookId, TO_READ);
    }

    public Integer getNumberOfUsersReadingBook(Long bookId) {
        return bookPossessionRepository.countAllByBookIdAndState(bookId, IN_PROGRESS);

    }

    public Integer getNumberOfUsersReadBook(Long bookId) {
        return bookPossessionRepository.countAllByBookIdAndState(bookId, FINISHED);
    }

    public BookPossessionDTO updateBookPossession(Long userId, Long bookId, UpdateBookPossessionRequest request) throws UnknownBookStatusException {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        switch (request.getBookState()) {
            case TO_READ -> addBookToToRead(user, book);
            case IN_PROGRESS -> addBookToInProgress(user, book, request.getCurrentPage());
            case FINISHED -> addBookToFinished(user, book);
            default -> throw new UnknownBookStatusException(request.getBookState().toString());
        }
        return bookPossessionDTOMapper.toDTO(getBookPossession(bookId, userId));
    }

    private void addBookToFinished(User user, Book book) {
        BookPossession bookPossession = bookPossessionRepository.findByBookIdAndUserId(
                book.getId(),
                user.getId()
        ).orElse(new BookPossession(book, user, LocalDateTime.now(), LocalDateTime.now()));

        bookPossession.setState(FINISHED);
        bookPossession.setFinishDate(LocalDateTime.now());
        bookPossession.setCurrentPage(book.getNumberOfPages());
        bookPossessionRepository.save(bookPossession);
    }

    private void addBookToInProgress(User user, Book book, Integer currentPage) {
        BookPossession bookPossession = bookPossessionRepository.findByBookIdAndUserId(
                book.getId(),
                user.getId()
        ).orElse(new BookPossession(book, user, LocalDateTime.now(), LocalDateTime.now()));

        bookPossession.setState(IN_PROGRESS);
        bookPossession.setCurrentPage(currentPage);
        bookPossessionRepository.save(bookPossession);
    }

    private void addBookToToRead(User user, Book book) {
        BookPossession bookPossession = bookPossessionRepository.findByBookIdAndUserId(
                book.getId(),
                user.getId()
        ).orElse(new BookPossession(book, user, LocalDateTime.now()));

        bookPossession.setState(TO_READ);
        bookPossession.setCurrentPage(0);
        bookPossession.setStartDate(null);
        bookPossession.setFinishDate(null);
        bookPossessionRepository.save(bookPossession);
    }

    public BookPossessionDTO deleteBookPossession(Long userId, Long bookId) {
        bookPossessionRepository.deleteAllByBookIdAndUserId(bookId, userId);
        return bookPossessionDTOMapper.toDTO(getBookPossession(bookId, userId));
    }
}
