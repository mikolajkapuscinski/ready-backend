package ai.ready.ready.user.dto;

import ai.ready.ready.book.BookCardDto;

import java.util.List;

public record ProfileDto (
        String username,
        String image,
        Integer numberOfFollowers,
        Integer numberOfFollowed,
        List<BookCardDto> currentlyReading,
        List<BookCardDto> recentlyFinished,
        List<BookCardDto> toRead,
        ReadingStats readingStats,
        List<Bagde> badges,
        List<Review> reviews
){}
