package ai.ready.ready.user.dto;

public record ReadingStats (
    Integer level,
    Double levelProgress,
    Integer numberOfReadBooks,
    Integer numberOfReadPages,
    Integer averageTimePerWeek
    )
{}
