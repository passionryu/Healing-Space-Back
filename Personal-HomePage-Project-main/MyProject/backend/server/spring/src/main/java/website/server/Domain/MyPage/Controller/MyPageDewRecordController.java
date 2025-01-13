package website.server.Domain.MyPage.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.server.Domain.HealingProgram.HealingService.DewRecord.DTO.Response.DiaryResponse;
import website.server.Domain.HealingProgram.HealingService.DewRecord.DTO.Response.DiaryThumbnailResponse;
import website.server.Domain.HealingProgram.HealingService.DewRecord.Service.DewService;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
@Tag(name = "MyPage", description = "마이페이지 서비스 API")
public class MyPageDewRecordController {

    private final DewService dewService;

    /**
     * 일기 리스트 조회 API
     * @param request 사용자 요청
     * @return 내 일기 리스트(diaryNumber,weather,date,title)
     */
    @Operation(summary = " 일기 리스트 조회 API ", description = " ")
    @GetMapping("/diary/show/list")
    public ResponseEntity<List<DiaryThumbnailResponse>> showDiaryList(HttpServletRequest request){

        /* 다이어리 DTO 리스트 반환 */
        List<DiaryThumbnailResponse> diaryThumbnailResponse = dewService.getDiaryThumbnails(request);

        return ResponseEntity.ok(diaryThumbnailResponse);
    }

    /**
     * 일기장 조회 API
     * @param request 사용자 요청
     * @return 일기장 (날짜,제목,본문,감정,힐링 메시지,힐링 뮤직)
     */
    @Operation(summary = " 일기장 조회 API ", description = " ")
    @GetMapping("/diary/show/{diaryNumber}")
    public ResponseEntity<DiaryResponse> showDiary(HttpServletRequest request,
                                                   @PathVariable(name = "diaryNumber")
                                                   Long diaryNumber){

        /* 선택한 날짜의 일기 데이터 반환 */
        DiaryResponse diaryResponse = dewService.getDiary(request,diaryNumber);

        return ResponseEntity.ok(diaryResponse);
    }

    /**
     * 일기 삭제 API
     * @param request 사용자 요청
     * @param date 일기 작성 날짜
     * @return 일기 삭제 성공 메시지
     */
    @Operation(summary = " 일기 삭제 API ", description = " ")
    @DeleteMapping("/diary")
    public ResponseEntity<String> deleteDiary(HttpServletRequest request,
                                              @RequestParam(name = "date")
                                              @DateTimeFormat(pattern = "yyyy-MM-dd")
                                              LocalDate date){

        /* 일기 삭제 */
        dewService.deleteDiary(request,date);

        return ResponseEntity.ok("일기 삭제 성공!");
    }
}
