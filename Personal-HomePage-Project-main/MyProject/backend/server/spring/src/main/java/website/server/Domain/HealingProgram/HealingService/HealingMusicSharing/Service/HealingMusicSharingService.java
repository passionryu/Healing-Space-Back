package website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Service;

import jakarta.servlet.http.HttpServletRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Request.PostRequest;

public interface HealingMusicSharingService {

    /**
     * 힐링 뮤직 쉐어링 메서드
     * @param request 사용자 요청
     * @param postRequest 포스트 요청 데이터
     */
    void postHealingMusic(HttpServletRequest request, PostRequest postRequest);


}
