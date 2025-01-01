package website.server.Domain.HealingProgram.HealingService.DewRecord.DTO.Response;

import java.time.LocalDate;

public record DiaryResponse(
        LocalDate date,
        String title,
        String diary,
        String emotion,
        String healingMessage,
        String healingMusic
){}
