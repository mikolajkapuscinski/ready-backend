package ai.ready.ready.user.role;

import ai.ready.ready.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Role{

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToMany
    private List<User> users;
}
