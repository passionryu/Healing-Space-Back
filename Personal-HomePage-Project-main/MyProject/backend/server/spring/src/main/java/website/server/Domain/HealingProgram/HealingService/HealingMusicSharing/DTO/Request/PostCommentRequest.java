package website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Request;

public record PostCommentRequest(
        Long musicId,
        String content
) {
}
