package ai.ready.ready.user.dto;

import ai.ready.ready.book.Book;
import java.util.List;

public record ProfileDto (
        String username,
        String image,
        List<Book> currentlyReading,
        List<Book> recentlyFinished,
        ReadingStats readingStats
){}
