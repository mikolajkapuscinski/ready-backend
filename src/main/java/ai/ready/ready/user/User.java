package ai.ready.ready.user;

import ai.ready.ready.bookPossesion.BookPossession;
import ai.ready.ready.review.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ready_users")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String imageUrl;
    private String username;
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @CreationTimestamp
    private Date creationDate;
    private boolean active;
    @OneToMany
    private List<Role> roles;
    @OneToMany(mappedBy = "user")
    private Set<BookPossession> books;
    private Long expPoints;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Badge> badges;
    @OneToMany
    private List<Review> review;

}
