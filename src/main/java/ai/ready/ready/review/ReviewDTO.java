package ai.ready.ready.review;

import ai.ready.ready.user.dto.UserDTO;

import java.time.LocalDateTime;

public record ReviewDTO(
        Long id,
        String title,
        UserDTO reviewer,
        String content,
        Double rating,
        LocalDateTime createdAt
) {
}
