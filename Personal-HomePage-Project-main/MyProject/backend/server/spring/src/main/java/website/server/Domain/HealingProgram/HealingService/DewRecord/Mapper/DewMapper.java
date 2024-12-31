package website.server.Domain.HealingProgram.HealingService.DewRecord.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import website.server.Domain.HealingProgram.HealingService.DewRecord.Entity.Diary;

import java.time.LocalDate;

@Mapper
public interface DewMapper {

    /**
     * 일기 저장 메서드
     * @param diary 일기 객체
     */
    void saveDiary(Diary diary);

    /**
     * 일기 삭제 메서드
     * @param userNumber 사용자 고유 번호
     * @param date 일기 작성 날짜
     */
    void deleteDiary(@Param("userNumber")Long userNumber, @Param("date") LocalDate date);

}
