package website.server.Domain.HealingProgram.HealingService.DewRecord.Mapper;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import website.server.Domain.HealingProgram.HealingService.DewRecord.DTO.Response.DiaryResponse;
import website.server.Domain.HealingProgram.HealingService.DewRecord.DTO.Response.DiaryThumbnailResponse;
import website.server.Domain.HealingProgram.HealingService.DewRecord.Entity.Diary;
import java.time.LocalDate;
import java.util.List;

@Mapper
public interface DewMapper {

    /**
     * 일기 저장
     * @param diary 일기 객체
     */
    void saveDiary(Diary diary);

    /**
     * 일기 삭제
     * @param userNumber 사용자 고유 번호
     * @param date 일기 작성 날짜
     */
    void deleteDiary(@Param("userNumber")Long userNumber, @Param("date") LocalDate date);

    /**
     * 일기 썸네일 리스트 반환
     * @param userNumber 사용자 고유 번호
     * @return 일기 썸네일 리스트
     */
    List<DiaryThumbnailResponse> getDiaryThumbnails(Long userNumber);

    /**
     * 선택한 날짜의 일기 조회
     * @param userNumber 사용자 고유 번호
     * @param date 일기 작성 날짜
     * @return 일기장 (날짜,제목,본문,감정,힐링 메시지,힐링 뮤직)
     */
    DiaryResponse getDiary(@Param("userNumber")Long userNumber, @Param("date") LocalDate date);

}
