package ai.ready.ready.bookPossesion.review;

import ai.ready.ready.bookPossesion.BookPossession;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Review {
    @Id
    @Column(name = "review_id")
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private BookPossession book;
    private String title;
    private Rating rating;
    private String content;
    private LocalDateTime createdAt;
}
