package website.server.Domain.HealingProgram.HealingService.DewCalendar.Service;

import website.server.Domain.HealingProgram.HealingService.DewCalendar.DTO.Request.DiaryRequest;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.DTO.Response.DiaryResponse;

public interface DewService {

    DiaryResponse saveDiary(DiaryRequest diaryRequest);


}
