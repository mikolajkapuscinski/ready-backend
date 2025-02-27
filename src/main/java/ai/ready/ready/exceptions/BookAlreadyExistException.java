package ai.ready.ready.exceptions;

public class BookAlreadyExistException extends RuntimeException {
    public BookAlreadyExistException(String bookName) {
        super("Book '" + bookName + "' already exists");
    }
}
