package ai.ready.ready;

import ai.ready.ready.book.Book;
import ai.ready.ready.book.BookState;
import ai.ready.ready.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserBooks {

    @EmbeddedId
    UserBookKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "student_id")
    User user;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    Book book;

    LocalDateTime possessionDate;
    Integer rating;
    BookState state;


}
