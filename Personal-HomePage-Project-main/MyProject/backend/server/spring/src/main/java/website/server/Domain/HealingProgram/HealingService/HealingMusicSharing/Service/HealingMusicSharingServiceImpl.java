package website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Request.PostRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Mapper.HealingMusicSharingMapper;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Util.HealingMusicVideoLinkUtil;
import website.server.Global.JWT.JwtService;

@Slf4j
@Service
@RequiredArgsConstructor
public class HealingMusicSharingServiceImpl implements  HealingMusicSharingService{

    private final HealingMusicSharingMapper healingMusicSharingMapper;
    private final JwtService jwtService;
    private final HealingMusicVideoLinkUtil healingMusicVideoLinkUtil;

    /**
     * 힐링 뮤직 쉐어링 메서드
     * @param request 사용자 요청
     * @param postRequest 포스트 요청 데이터
     */
    @Override
    public void postHealingMusic(HttpServletRequest request, PostRequest postRequest) {

        /* 사용자 요청에서 사용자 고유번호 추출 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        // 유튜브 URL로 썸네일 URL 추출
        String thumbnailUrl = healingMusicVideoLinkUtil.extractThumbnailUrl(postRequest.videoLink());
        log.info("Thumbnail Url: {}", thumbnailUrl);

        if (thumbnailUrl == null) {
            // 유효한 썸네일 URL을 추출할 수 없는 경우 처리
            throw new RuntimeException("썸네일 추출 실패");
        }

        /* DB 저장 */
        healingMusicSharingMapper.HealingMusicSharing(userNumber,
                                                      postRequest.title(),
                                                      postRequest.content(),
                                                      thumbnailUrl,
                                                      postRequest.videoLink());


    }
}
