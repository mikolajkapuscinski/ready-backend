package ai.ready.ready.book.dto;

import ai.ready.ready.bookPossesion.BookPossessionDTO;
import ai.ready.ready.review.ReviewDTO;

import java.util.Date;
import java.util.List;

public record BookDTO(
        Long id,
        String title,
        String author,
        String isbn13,
        String coverURL,
        String language,
        Integer numberOfPages,
        String description,
        Date dateOfPublication,
        List<ReviewDTO> reviews,
        Integer avgRating,
        Integer numberOfToReads,
        Integer numberOfCurrentlyReading,
        Integer numberOfRead,
        BookPossessionDTO bookPossession
) {
}
