package ai.ready.ready.book;

import org.springframework.data.jpa.domain.Specification;

public class BookSpecs {

    public static Specification<Book> containsTitle(final String providedTitle) {
        return (root, query, criteriaBuilder) -> {
            var lowerCaseValue = criteriaBuilder.lower(root.get("title"));
            String likeStatement = "%" + providedTitle.toLowerCase() + "%";
            return criteriaBuilder.like(lowerCaseValue, likeStatement);
        };
    }

    public static Specification<Book> containsAuthor(final String providedAuthor) {
        return (root, query, criteriaBuilder) -> {
            var lowerCaseValue = criteriaBuilder.lower(root.get("author"));
            String likeStatement = "%" + providedAuthor.toLowerCase() + "%";
            return criteriaBuilder.like(lowerCaseValue, likeStatement);
        };
    }

    public static Specification<Book> containsIsbn(final String providedIsbn) {
        return (root, query, criteriaBuilder) -> {
            var lowerCaseValue = criteriaBuilder.lower(root.get("isbn13"));
            String likeStatement = "%" + providedIsbn.toLowerCase() + "%";
            return criteriaBuilder.like(lowerCaseValue, likeStatement);
        };
    }
}
