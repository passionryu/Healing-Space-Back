package website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Request;

public record CommentRequest(
        Long messageId,
        String comment
) {
}
