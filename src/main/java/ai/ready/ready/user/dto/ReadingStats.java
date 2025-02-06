package ai.ready.ready.user.dto;

public record ReadingStats (
    Integer level,
    Integer levelProgress,
    Integer numberOfReadBooks,
    Integer numberOfReadPages,
    Integer averageTimePerWeek
    )
{}
