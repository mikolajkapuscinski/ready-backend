package ai.ready.ready.bookPossesion;

import java.time.LocalDateTime;

public record BookPossessionDTO(
        BookState state,
        Integer currentPage,
        LocalDateTime finishDate
)
{
}
