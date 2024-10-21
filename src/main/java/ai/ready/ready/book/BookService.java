package ai.ready.ready.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getBooks(String title, String author) {
        if (title == null && author == null)
            return (List<Book>) bookRepository.findAll();
        if (title == null)
            return bookRepository.findByAuthor(author);
        if (author == null)
            return bookRepository.findByTitle(title);

        return bookRepository.findByTitleAndAuthor(title, author);
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
}
