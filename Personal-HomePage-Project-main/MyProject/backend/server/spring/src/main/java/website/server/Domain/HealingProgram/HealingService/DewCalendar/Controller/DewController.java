package website.server.Domain.HealingProgram.HealingService.DewCalendar.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.DTO.Request.DiaryRequest;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.DTO.Request.FullDiaryRequest;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.DTO.Response.AiResponse;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.Entity.Diary;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.Mapper.DewMapper;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.Service.DewService;
import website.server.Global.JWT.JwtService;

import java.time.LocalDate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/dew")
@Tag(name = "DEW Calendar", description = "Dew 캘린더 서비스 API")
public class DewController {

    /*
    * 서비스 흐름상 일기 수정은 없음
    * */

    private final DewService dewService;
    private final DewMapper dewMapper;
    private final JwtService jwtService;

    /**
     * 일기 저장 API
     * @param request 사용자 요청
     * @param diaryRequest 일기 제목,본문
     * @return 응답 DTO (감정,글귀,음악)
     */
    // todo 하루에 한번만 작성
    @Operation(summary = " 일기 저장 ", description = " ")
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

    /* 일기 조회 */
    /*
    * 서비스 기획
    * 1.달력 형태로 조회할지
    * 2.리스트 형식으로 조회할지
    * */

    /* 일기 삭제 */
    @Operation(summary = " 일기 삭제 ", description = " ")
    @DeleteMapping("/diary")
    public ResponseEntity<String> deleteDiary(HttpServletRequest request,LocalDate date){



        return ResponseEntity.ok("일기 삭제 성공!");
    }

}
