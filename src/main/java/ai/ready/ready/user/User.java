package ai.ready.ready.user;

import ai.ready.ready.BookPossession;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "ready_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    private LocalDate creationDate;
    private boolean active;

    @OneToMany(mappedBy = "user")
    private Set<BookPossession> books;

}
