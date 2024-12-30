package website.server.Domain.HealingProgram.HealingService.DewCalendar.DTO.Response;

public record AiResponse(
        String emotion,
        String weather,
        String healingMessage,
        String healingMusic
) {}
