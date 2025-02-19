package ai.ready.ready.book;

import ai.ready.ready.exceptions.BookNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookCardDtoMapper bookCardDtoMapper;
    private final BookDTOMapper bookDTOMapper;

    public List<BookCardDto> getBooks(String title, String author) {
        if (title == null && author == null)
            return  bookCardDtoMapper.toBookCardDtos((List<Book>) bookRepository.findAll());
        if (title == null)
            return bookCardDtoMapper.toBookCardDtos(bookRepository.findByAuthor(author));
        if (author == null)
            return bookCardDtoMapper.toBookCardDtos(bookRepository.findByTitle(title));

        return bookCardDtoMapper.toBookCardDtos(bookRepository.findByTitleAndAuthor(title, author));
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        return bookDTOMapper.toBookDTO(book);
    }
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }


}
