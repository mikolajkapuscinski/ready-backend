package ai.ready.ready.exceptions;

public class UnknownBookStatusException extends Throwable {
    public UnknownBookStatusException(String s) {
        super("Unexpected book status: " + s);
    }
}
