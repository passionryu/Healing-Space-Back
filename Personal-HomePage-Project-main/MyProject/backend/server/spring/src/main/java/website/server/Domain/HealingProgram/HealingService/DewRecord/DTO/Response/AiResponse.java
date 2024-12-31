package website.server.Domain.HealingProgram.HealingService.DewRecord.DTO.Response;

public record AiResponse(
        String emotion,
        String weather,
        String healingMessage,
        String healingMusic
) {}
