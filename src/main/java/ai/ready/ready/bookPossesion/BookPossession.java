package ai.ready.ready.bookPossesion;

import ai.ready.ready.book.Book;
import ai.ready.ready.review.Review;
import ai.ready.ready.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "book_possession")
@NoArgsConstructor
public class BookPossession {

    public BookPossession(Book book, User user, LocalDateTime possessionDate) {
        this.book = book;
        this.user = user;
        this.possessionDate = possessionDate;
    }

    public BookPossession(Book book, User user, LocalDateTime possessionDate, LocalDateTime startDate) {
        this(book, user, possessionDate);
        this.startDate = startDate;
    }

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
    @CreationTimestamp
    LocalDateTime possessionDate;
    @Enumerated(EnumType.STRING)
    BookState state;
    Integer currentPage;
    LocalDateTime startDate;
    LocalDateTime finishDate;
    @OneToOne
    @JoinColumn(name = "review_id")
    Review review;
}
