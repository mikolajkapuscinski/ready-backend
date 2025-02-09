package ai.ready.ready.book.bookPossesion;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, Double> {
    @Override
    public Double convertToDatabaseColumn(Rating rating) {
        if(rating == null) {
            return null;
        }
        return rating.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(Double ratingValue) {
        if(ratingValue == null) {
            return null;
        }
        return Stream.of(Rating.values())
                .filter(r -> r.getValue().equals(ratingValue))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
