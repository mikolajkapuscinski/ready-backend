package ai.ready.ready.user.dto;

import ai.ready.ready.book.BookCardDto;
import ai.ready.ready.bookPossesion.Review;
import ai.ready.ready.user.Badge;

import java.util.Date;
import java.util.List;

public record ProfileDto (
        String username,
        String image,
        Date dateOfJoin,
        List<BookCardDto> currentlyReading,
        List<BookCardDto> recentlyFinished,
        List<BookCardDto> toRead,
        ReadingStats readingStats,
        List<Badge> badges,
        List<Review> reviews
){}
