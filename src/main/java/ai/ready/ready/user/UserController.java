package ai.ready.ready.user;

import ai.ready.ready.book.dto.BookCardDto;
import ai.ready.ready.bookPossesion.BookPossessionService;
import ai.ready.ready.bookPossesion.UpdateBookPossessionRequest;
import ai.ready.ready.exceptions.BookNotFoundException;
import ai.ready.ready.exceptions.UnknownBookStatus;
import ai.ready.ready.security.authentication.dto.UserDetailsModel;
import ai.ready.ready.user.dto.ProfileDto;
import ai.ready.ready.user.dto.UpdateProfileRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final BookPossessionService bookPossessionService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/me")
    public ProfileDto getProfile(@AuthenticationPrincipal UserDetailsModel userDetails) {
        return userService.getProfile(userDetails);
    }

    @PatchMapping("/me")
    public ResponseEntity updateProfile(@AuthenticationPrincipal UserDetailsModel userDetails, UpdateProfileRequest updateProfileRequest){
        userService.updateProfile(userDetails, updateProfileRequest);
        return new ResponseEntity( HttpStatus.ACCEPTED);
    }

    @GetMapping("/me/books")
    public List<BookCardDto> getUsersBook(@AuthenticationPrincipal UserDetailsModel userDetails, @RequestParam(required = false) String status) throws UnknownBookStatus {
        return switch (status) {
            case null -> bookPossessionService.getUsersBook(userDetails.getId());
            case "to_read" -> bookPossessionService.getToReadByUserId(userDetails.getId());
            case "currently_reading" -> bookPossessionService.getCurrentlyReadingByUserId(userDetails.getId());
            case "finished" -> bookPossessionService.getRecentlyFinishedByUserId(userDetails.getId());
            default -> throw new UnknownBookStatus(status);
        };
    }

    @PutMapping("/me/books/{bookId}")
    public ResponseEntity updateBookDetails(
            @AuthenticationPrincipal UserDetailsModel userDetails,
            @PathVariable Long bookId,
            @RequestBody UpdateBookPossessionRequest request
    ) throws UnknownBookStatus, BookNotFoundException {
            bookPossessionService.updateBookPossession(
                userDetails.getId(),
                bookId,
                request
            );
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/me/books/{bookId}")
    public ResponseEntity deleteBookDetails(
            @AuthenticationPrincipal UserDetailsModel userDetailsModel,
            @PathVariable Long bookId
    ){
        bookPossessionService.deleteBookPossession(userDetailsModel.getId(), bookId);
        return new ResponseEntity( HttpStatus.ACCEPTED);
    }
}
