package ai.ready.ready.bookPossesion;

import java.time.LocalDateTime;

public record BookPossessionDTO(
        BookState bookState,
        Integer currentPage,
        LocalDateTime finishDate
)
{
}
