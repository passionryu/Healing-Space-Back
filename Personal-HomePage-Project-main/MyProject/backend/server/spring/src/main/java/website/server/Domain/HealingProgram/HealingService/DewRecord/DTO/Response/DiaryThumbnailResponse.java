package website.server.Domain.HealingProgram.HealingService.DewRecord.DTO.Response;

import java.time.LocalDate;

public record DiaryThumbnailResponse(
        Long diaryNumber,
        String weather,
        LocalDate date,
        String title
) {
}
