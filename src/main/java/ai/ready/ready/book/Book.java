package ai.ready.ready.book;

import ai.ready.ready.BookPossession;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    private String title;
    private String author;
    private Integer numberOfPages;

    @OneToMany(mappedBy = "book")
    Set<BookPossession> owners;

}
