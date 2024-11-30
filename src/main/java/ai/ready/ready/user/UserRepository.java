package ai.ready.ready.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    //TODO
    @Query("Select b, u from Book b left join BookPossession bp on bp.book.id = b.id left join User u on bp.user.id = u.id where u.id = :userId")
    Integer findTotalPagesByUserId(@Param("userId") Long userId);

    //TODO
    @Query("Select COUNT(*) from Book b cross join BookPossession bp cross join User u where u.id = :userId")
    Integer findTotalBooksByUserId(@Param("userId") Long userId);
}
