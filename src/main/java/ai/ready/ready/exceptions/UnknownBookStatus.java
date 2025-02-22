package ai.ready.ready.exceptions;

public class UnknownBookStatus extends Throwable {
    public UnknownBookStatus(String s) {
        super("Unexpected book status: " + s);
    }
}
