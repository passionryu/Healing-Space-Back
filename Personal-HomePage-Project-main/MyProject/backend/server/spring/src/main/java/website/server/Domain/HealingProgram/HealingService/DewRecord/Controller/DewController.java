package website.server.Domain.HealingProgram.HealingService.DewRecord.Controller;

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
import website.server.Domain.HealingProgram.HealingService.DewRecord.DTO.Request.DiaryRequest;
import website.server.Domain.HealingProgram.HealingService.DewRecord.DTO.Response.AiResponse;
import website.server.Domain.HealingProgram.HealingService.DewRecord.Service.DewService;
import website.server.Global.JWT.JwtService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/dew")
@Tag(name = "DEW Record", description = "Dew 레코드 서비스 API")
public class DewController {

    /*
    * 서비스 흐름상 일기 수정은 없음, 일기는 하루에 한번씩만 저장
    * 다시 한번 일기 작성 요청이 오면, "일기는 하루에 한번만 작성 가능합니다"라는 문구로 안내하기
    * */

    private final DewService dewService;
    private final JwtService jwtService;

    /**
     * 일기 작성 API
     * @param request 사용자 요청
     * @param diaryRequest 일기 제목,본문
     * @return 응답 DTO (감정,글귀,음악)
     */
    @Operation(summary = " 일기 작성 API ", description = " ")
    @PostMapping("/diary")
    public ResponseEntity<AiResponse> saveDiary(HttpServletRequest request, @RequestBody DiaryRequest diaryRequest){

        /* AI 연산 */
        AiResponse aiResponse = dewService.AiCalculation(diaryRequest);

        /* 일기 최종 저장 */
        dewService.saveDiary(jwtService.extractUserNumberFromRequest(request),diaryRequest,aiResponse);

        return ResponseEntity.ok(aiResponse);
    }

}
