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
        this.id = new BookPossessionKey(book.getId(), user.getId());
    }

    public BookPossession(Book book, User user, LocalDateTime possessionDate, LocalDateTime startDate) {
        this(book, user, possessionDate);
        this.startDate = startDate;
    }

    @EmbeddedId
    private BookPossessionKey id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;
    @CreationTimestamp
    private LocalDateTime possessionDate;
    @Enumerated(EnumType.STRING)
    private BookState state;
    private Integer currentPage;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    @OneToOne
    @JoinColumn(name = "review_id")
    private Review review;
}
