package ai.ready.ready.exceptions;

public class VerificationTokenExpired extends RuntimeException {
    public VerificationTokenExpired() {
        super("Token has expired");
    }
}
