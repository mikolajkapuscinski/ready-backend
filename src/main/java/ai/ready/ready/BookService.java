package ai.ready.ready;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    public List<Book> getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
    public List<Book> getBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
