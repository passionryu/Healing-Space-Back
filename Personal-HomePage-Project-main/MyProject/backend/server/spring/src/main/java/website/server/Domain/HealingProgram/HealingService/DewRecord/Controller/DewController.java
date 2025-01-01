package website.server.Domain.HealingProgram.HealingService.DewRecord.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.server.Domain.HealingProgram.HealingService.DewRecord.DTO.Request.DiaryRequest;
import website.server.Domain.HealingProgram.HealingService.DewRecord.DTO.Response.AiResponse;
import website.server.Domain.HealingProgram.HealingService.DewRecord.DTO.Response.DiaryThumbnailResponse;
import website.server.Domain.HealingProgram.HealingService.DewRecord.Service.DewService;
import website.server.Global.JWT.JwtService;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/dew")
@Tag(name = "DEW Calendar", description = "Dew 캘린더 서비스 API")
public class DewController {

    /*
    * 서비스 흐름상 일기 수정은 없음, 일기는 하루에 한번씩만 저장
    * 다시 한번 일기 작성 요청이 오면, "일기는 하루에 한번만 작성 가능합니다"라는 문구로 안내하기
    * */

    private final DewService dewService;
    private final JwtService jwtService;

    /**
     * 일기 저장 API
     * @param request 사용자 요청
     * @param diaryRequest 일기 제목,본문
     * @return 응답 DTO (감정,글귀,음악)
     */
    @Operation(summary = " 일기 저장 API ", description = " ")
    @PostMapping("/diary")
    public ResponseEntity<AiResponse> saveDiary(HttpServletRequest request, @RequestBody DiaryRequest diaryRequest){

        /* AI 연산 */
        AiResponse aiResponse = dewService.AiCalculation(diaryRequest);

        /* 사용자 고유번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* 일기 최종 저장 */
        dewService.saveDiary(userNumber,diaryRequest,aiResponse);

        return ResponseEntity.ok(aiResponse);
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

    /**
     * 일기 리스트 조회 API
     * @param request 사용자 요청
     * @return 내 일기 리스트(diaryNumber,weather,date,title)
     */
    @Operation(summary = " 일기 리스트 조회 API ", description = " ")
    @PostMapping("/diary/show/list")
    public ResponseEntity<List<DiaryThumbnailResponse>> showDiaryList(HttpServletRequest request){

        log.info("request :{}", request);
        /* 다이어리 DTO 리스트 반환 */
        List<DiaryThumbnailResponse> diaryThumbnailResponse = dewService.getDiaryThumbnails(request);

        return ResponseEntity.ok(diaryThumbnailResponse);
    }

    /**
     * 선택한 일기 조회 API
     * @param request 사용자 요청
     * @return 일기장 (제목,본문,감정,힐링 메시지,힐링 뮤직)
     */
    @Operation(summary = " 선택한 일기 조회 API ", description = " ")
    @PostMapping("/diary/show")
    public ResponseEntity<String> showDiary(HttpServletRequest request){


        return ResponseEntity.ok("일기 조회 성공!");
    }





    /* 일기 조회 */
    // 1번 방법. 달력 형태로 조회
    // 2번 방법. 리스트 형태로 조회



}
