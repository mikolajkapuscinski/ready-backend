package ai.ready.ready;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class UserBookKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "book_id")
    Long bookId;
}
