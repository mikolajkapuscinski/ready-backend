package ai.ready.ready.book;

import ai.ready.ready.bookPossesion.BookPossession;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
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
    private String isbn13;
    private String isbn10;
    private String coverUrl;
    private String language;
    private Integer numberOfPages;
    private Date dateOfPublication;
    String description;
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    Set<BookPossession> owners;

}
