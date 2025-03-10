package ai.ready.ready.bookPossesion;

import ai.ready.ready.book.dto.BookCardDto;
import ai.ready.ready.exceptions.BookNotFoundException;
import ai.ready.ready.exceptions.UnknownBookStatusException;
import ai.ready.ready.security.authentication.dto.UserDetailsModel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("users/me/books")
public class BookPossessionController {

    private final BookPossessionService bookPossessionService;

    @GetMapping
    public List<BookCardDto> getUsersBook(@AuthenticationPrincipal UserDetailsModel userDetails, @RequestParam(required = false) String status) throws UnknownBookStatusException {
        return switch (status) {
            case null -> bookPossessionService.getUsersBook(userDetails.getId());
            case "to_read" -> bookPossessionService.getToReadByUserId(userDetails.getId());
            case "currently_reading" -> bookPossessionService.getCurrentlyReadingByUserId(userDetails.getId());
            case "finished" -> bookPossessionService.getRecentlyFinishedByUserId(userDetails.getId());
            default -> throw new UnknownBookStatusException(status);
        };
    }

    @PutMapping("/{bookId}")
    public ResponseEntity updateBookDetails(
            @AuthenticationPrincipal UserDetailsModel userDetails,
            @PathVariable Long bookId,
            @RequestBody UpdateBookPossessionRequest request
    ) throws UnknownBookStatusException, BookNotFoundException {
        bookPossessionService.updateBookPossession(
                userDetails.getId(),
                bookId,
                request
        );
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity deleteBookDetails(
            @AuthenticationPrincipal UserDetailsModel userDetailsModel,
            @PathVariable Long bookId
    ){
        bookPossessionService.deleteBookPossession(userDetailsModel.getId(), bookId);
        return new ResponseEntity( HttpStatus.ACCEPTED);
    }
}
