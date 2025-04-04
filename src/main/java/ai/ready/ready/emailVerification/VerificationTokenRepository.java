package ai.ready.ready.emailVerification;

import ai.ready.ready.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByUser(User user);
    Optional<VerificationToken> findByToken(String token);
}
