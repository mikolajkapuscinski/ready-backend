package ai.ready.ready.exceptions;

public class NotValidVerificationToken extends RuntimeException {
    public NotValidVerificationToken() {
        super("Invalid verification token");
    }
}
