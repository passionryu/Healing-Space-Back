package website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Request.HealingMessageCreateRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.Mapper.HealingMessageMapper;
import website.server.Global.JWT.JwtService;

@Slf4j
@Service
@RequiredArgsConstructor
public class HealingMessageServiceImpl implements HealingMessageService{

    private final HealingMessageMapper healingMessageMapper;
    private final JwtService jwtService;

    /**
     * 힐링 메시지 쉐어링 메서드
     * @param request 사용자 요청
     * @param healingMessageCreateRequest 힐링 메시지 입력 데이터
     */
    @Override
    public void create(HttpServletRequest request, HealingMessageCreateRequest healingMessageCreateRequest) {

        /* 사용자 고유 번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* 힐링 메시지 게시글 저장 */
        healingMessageMapper.create(userNumber,
                healingMessageCreateRequest.title(),
                healingMessageCreateRequest.imagePath(),
                healingMessageCreateRequest.content());

    }

    /**
     * 힐링 메시지 삭제 메서드
     * @param request 사용자 요청
     * @param messageId 삭제할 힐링 메시지 고유 번호
     */
    @Override
    public void delete(HttpServletRequest request, Long messageId) {


    }
}
