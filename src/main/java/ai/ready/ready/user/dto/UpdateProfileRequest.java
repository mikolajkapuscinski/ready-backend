package ai.ready.ready.user.dto;

public record UpdateProfileRequest(
        String username,
        String aboutMe,
        String imageUrl
) {
}
