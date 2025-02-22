package ai.ready.ready.book;

import ai.ready.ready.security.authentication.dto.UserDetailsModel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookCardDto> getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author
    ) {
        return bookService.getBooks(title, author);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id, @AuthenticationPrincipal UserDetailsModel user) {
        return new ResponseEntity<>(bookService.getBookById(id, user.getId()), HttpStatus.OK);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }


    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }


}



