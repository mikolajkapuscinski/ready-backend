package ai.ready.ready.exceptions;

public class EmailNotConfirmedException extends RuntimeException {
    public EmailNotConfirmedException() {
        super("Email coupled with this account was not confirmed");
    }
}
