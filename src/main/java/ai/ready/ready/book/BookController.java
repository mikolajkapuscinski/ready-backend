package ai.ready.ready.book;

import ai.ready.ready.book.dto.BookCardDto;
import ai.ready.ready.book.dto.BookCreationRequest;
import ai.ready.ready.book.dto.BookDTO;
import ai.ready.ready.book.dto.SearchRequest;
import ai.ready.ready.security.authentication.dto.UserDetailsModel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Page<BookCardDto>> getBooks(@RequestBody(required = false) SearchRequest searchCriteria, Pageable pageable) {
        return new ResponseEntity<>(bookService.getBooks(searchCriteria, pageable), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id, @AuthenticationPrincipal UserDetailsModel user) {
        return new ResponseEntity<>(bookService.getBookById(id, user.getId()), HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Book addBook(
            @RequestParam MultipartFile cover,
            @RequestPart BookCreationRequest bookDetails) {
        return bookService.createBook(bookDetails, cover);
    }


    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }


}



