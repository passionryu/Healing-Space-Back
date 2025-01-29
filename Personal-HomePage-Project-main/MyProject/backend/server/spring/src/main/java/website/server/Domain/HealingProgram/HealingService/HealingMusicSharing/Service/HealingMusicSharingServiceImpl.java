package website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Request.PostRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Response.HealingMusicListResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Response.HealingMusicResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Mapper.HealingMusicSharingMapper;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Util.HealingMusicVideoLinkUtil;
import website.server.Global.JWT.JwtService;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HealingMusicSharingServiceImpl implements  HealingMusicSharingService{

    private final JwtService jwtService;
    private final HealingMusicSharingMapper healingMusicSharingMapper;
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

        /* 유튜브 URL로 썸네일 URL 추출 */
        String thumbnailUrl = healingMusicVideoLinkUtil.extractThumbnailUrl(postRequest.videoLink());

        /* 썸네일 추출 실페 시 예외 처리 */
        if (thumbnailUrl == null)
            throw new RuntimeException("썸네일 추출 실패");

        /* DB에 힐링 뮤직 데이터 최종 저장 */
        healingMusicSharingMapper.HealingMusicSharing(userNumber,
                                                      postRequest.title(),
                                                      postRequest.content(),
                                                      thumbnailUrl,
                                                      postRequest.videoLink());
    }

    /**
     * 게시판에서 힐링 뮤직 리스트 조회 메서드
     * @return 힐링 뮤직 리스트 반환
     */
    @Override
    public List<HealingMusicListResponse> getHealingMusicList() {

        /* DB에서 힐링 뮤직 리스트 조회 */
        return healingMusicSharingMapper.getHealingMusicList();
    }

    /**
     * 게시판에서 힐링 뮤직 상세 조회 메서드
     * @param musicId
     * @return
     */
    @Override
    public HealingMusicResponse getHealingMusic(String musicId) {

        /* 힐링 뮤직 상세 조회 */
        return healingMusicSharingMapper.getHealingMusic(musicId);
    }






}
