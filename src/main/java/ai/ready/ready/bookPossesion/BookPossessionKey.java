package ai.ready.ready.bookPossesion;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class BookPossessionKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "book_id")
    Long bookId;
}
