package website.server.Domain.MyPage.Service.MypageService;

import jakarta.servlet.http.HttpServletRequest;
import website.server.Domain.MyPage.DTO.Response.mypage.MyInformationResponse;

public interface MypageService {

    /**
     * 내 정보 조회 메서드
     * @param request 사용자 요청
     * @return 프로필 정보 DTO
     */
    MyInformationResponse getMyInfo(HttpServletRequest request);

}
