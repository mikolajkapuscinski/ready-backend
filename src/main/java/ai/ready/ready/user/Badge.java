package ai.ready.ready.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Badge {
    @Id
    private Long id;
    private String title;
    private String description;
    private String imageUrl;

    @ManyToMany
    private List<User> users;
}
