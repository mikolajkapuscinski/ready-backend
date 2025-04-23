package ai.ready.ready.book.dto;

import java.util.Date;

public record BookCreationRequest(
        String title,
        String author,
        String isbn13,
        String language,
        Integer numberOfPages,
        String description,
        Date dateOfPublication
) {
}
