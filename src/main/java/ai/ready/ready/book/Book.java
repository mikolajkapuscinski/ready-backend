package ai.ready.ready.book;

import ai.ready.ready.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Set;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private Integer numberOfPages;

    @OneToMany(mappedBy = "user")
    Set<User> owners;

}
