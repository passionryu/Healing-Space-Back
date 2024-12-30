package website.server.Domain.HealingProgram.HealingService.DewCalendar.Mapper;

import org.apache.ibatis.annotations.Mapper;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.Entity.Diary;

@Mapper
public interface DewMapper {

    /**
     * 일기 저장 메서드
     * @param diary 일기 객체
     */
    void saveDiary(Diary diary);

}
