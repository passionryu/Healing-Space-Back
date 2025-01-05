package website.server.Domain.MyPage.Service.MypageService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import website.server.Domain.Member.Mapper.MemberMapper;
import website.server.Domain.MyPage.DTO.Request.mypage.ChangeInformationRequest;
import website.server.Domain.MyPage.DTO.Response.mypage.MyInformationResponse;
import website.server.Global.JWT.JwtService;

@Slf4j
@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService{

    private final MemberMapper memberMapper;
    private final JwtService jwtService;

    /**
     * 내 프로필 조회 메서드
     * @param request 사용자 요청
     * @return 프로필 조회 DTO
     */
    @Override
    public MyInformationResponse getMyInfo(HttpServletRequest request) {

        /* 사용자 고유번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* 프로필 정보 조회 */
        MyInformationResponse myInformationResponse = memberMapper.getMyInfo(userNumber);

        return myInformationResponse;
    }

    /**
     * 내 프로필 수정 메서드
     * @param request 사용자 요청
     * @return 수정된 프로필 DTO
     */
    @Override
    public MyInformationResponse changeInfo(HttpServletRequest request,
                                            ChangeInformationRequest changeInformationRequest){

        /* 사용자 고유번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        log.info("profileimagepath : {}" ,changeInformationRequest.ProfileImagePath());
        log.info("nickName : {}", changeInformationRequest.nickName());
        log.info("intro : {} " ,changeInformationRequest.intro());

        /* 내 프로필 수정 */
        memberMapper.changeMyInfo(userNumber,
                                changeInformationRequest.ProfileImagePath(),
                                changeInformationRequest.nickName(),
                                changeInformationRequest.intro());

        /* 수정된 프로필 정보 조회 */
        MyInformationResponse myInformationResponse = memberMapper.getMyInfo(userNumber);

        return myInformationResponse;
    }

}
