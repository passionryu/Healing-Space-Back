package website.server.Domain.HealingProgram.HealingService.DewCalendar.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    private final DewService dewService;
    private final DewMapper dewMapper;
    private final JwtService jwtService;

    /**
     * 일기 저장 API
     * @param request 사용자 요청
     * @param diaryRequest 일기 제목,본문
     * @return 응답 DTO (감정,글귀,음악)
     */
    @Operation(summary = " 일기 저장 ", description = " ")
    @PostMapping("/diary")
    public ResponseEntity<AiResponse> saveDiary(HttpServletRequest request, @RequestBody DiaryRequest diaryRequest){

        AiResponse aiResponse = dewService.saveDiary(diaryRequest);
        String AccessToken = jwtService.extractAccessToken(request);
        Long userNumber = jwtService.extractUserNumberFromToken(AccessToken);

        FullDiaryRequest fullDiaryRequest = new FullDiaryRequest(
                null,
                userNumber,
                diaryRequest.title(),
                diaryRequest.diary(),
                aiResponse.emotion(),
                aiResponse.weather(),
                LocalDate.now(),  // 현재 날짜를 사용 (원하는 날짜로 변경 가능)
                aiResponse.healingMessage(),
                aiResponse.healingMusic()
        );

        Diary diary = fullDiaryRequest.toEntity();

        dewMapper.saveDiary(diary);

        return ResponseEntity.ok(aiResponse);
    }

    /* 일기 조회 */

    /* 일기 수정 */

    /* 일기 삭제 */

}
