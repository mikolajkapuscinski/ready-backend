package ai.ready.ready.bookPossesion.review;

import ai.ready.ready.user.dto.UserDTO;

import java.util.Date;

public record ReviewDTO(
        Long id,
        String title,
        UserDTO user,
        String content,
        Integer rating,
        Date createdAt
) {
}
