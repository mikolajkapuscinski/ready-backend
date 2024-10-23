package ai.ready.ready.user;

import ai.ready.ready.book.Book;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private LocalDate creationDate;
    private boolean active;

    @OneToMany(mappedBy = "user")
    private Set<Book> books;

}
