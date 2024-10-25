package ai.ready.ready;

import ai.ready.ready.book.Book;
import ai.ready.ready.book.BookState;
import ai.ready.ready.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "book_possession")
public class BookPossession {

    @EmbeddedId
    BookPossessionKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    Book book;

    LocalDateTime possessionDate;
    Integer rating;
    BookState state;


}
