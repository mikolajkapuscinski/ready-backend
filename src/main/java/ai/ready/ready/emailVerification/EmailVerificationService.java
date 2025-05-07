package ai.ready.ready.emailVerification;

import ai.ready.ready.exceptions.NotValidVerificationToken;
import ai.ready.ready.exceptions.VerificationTokenExpired;
import ai.ready.ready.user.User;
import ai.ready.ready.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    private final VerificationTokenRepository verificationTokenRepository;

    public void sendVerificationEmail(User user) {
        String token = UUID.randomUUID().toString();
        createVerificationToken(user, token);

        String recipient = user.getEmail();
        String subject = "Email confirmation";
        String confirmationUrl = "http://localhost:5173" + "/#/registrationConfirm?token=" + token;
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(recipient);
        mail.setSubject(subject);
        mail.setText(confirmationUrl);
        mailSender.send(mail);
    }

    private void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    public void confirmEmail(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token).orElseThrow(NotValidVerificationToken::new);
        User user = verificationToken.getUser();
        if (verificationToken.getExpiryDate().before(new Date())){
            throw new VerificationTokenExpired();
        }
        user.setEnabled(true);
        userRepository.save(user);
    }
}
