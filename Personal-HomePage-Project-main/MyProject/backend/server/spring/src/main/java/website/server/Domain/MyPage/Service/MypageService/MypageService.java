package website.server.Domain.MyPage.Service.MypageService;

import jakarta.servlet.http.HttpServletRequest;
import website.server.Domain.MyPage.DTO.Request.mypage.ChangeInformationRequest;
import website.server.Domain.MyPage.DTO.Response.mypage.MyInformationResponse;

public interface MypageService {

    /**
     * 내 정보 조회 메서드
     * @param request 사용자 요청
     * @return 프로필 정보 DTO
     */
    MyInformationResponse getMyInfo(HttpServletRequest request);

    /**
     * 내 프로필 수정 메서드
     * @param request 사용자 요청
     * @return 수정된 프로필 DTO
     */
    MyInformationResponse changeInfo(HttpServletRequest request, ChangeInformationRequest changeInformationRequest);

}
