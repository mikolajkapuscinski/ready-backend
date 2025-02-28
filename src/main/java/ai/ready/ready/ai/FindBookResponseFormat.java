package ai.ready.ready.ai;

import com.fasterxml.jackson.annotation.JsonProperty;
public record FindBookResponseFormat(
        @JsonProperty(required = true, value = "title") String title,
        @JsonProperty(required = true, value = "author") String author,
        @JsonProperty(required = true, value = "isbn13") String isbn13,
        @JsonProperty(required = true, value = "language") String language,
        @JsonProperty(required = true, value = "numberOfPages") Integer numberOfPages,
        @JsonProperty(required = true, value = "description") String description,
        @JsonProperty(required = true, value = "dateOfPublication") String dateOfPublication
) {
}
