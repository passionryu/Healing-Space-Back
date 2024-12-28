package website.server.Domain.HealingProgram.HealingService.DewCalendar.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data //getter,setter,tostring,EqualsAndHashCode,RequiredArgsConstructor 포함
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diary {

    private Long diaryNumber;
    private String title;
    private String diary;
    private String emotion;
    private String weather;
    private LocalDate date;

}
