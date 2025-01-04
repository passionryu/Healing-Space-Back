package website.server.Domain.MyPage.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import website.server.Domain.MyPage.DTO.Response.mypage.MyInformationResponse;
import website.server.Domain.MyPage.Service.MypageService.MypageService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
@Tag(name = "MyPage", description = "마이페이지 서비스 API")
public class MyPageController {

    private final MypageService mypageService;

    /**
     * 내 정보 조회 API
     * @param request 사용자 요청
     * @return 프로필 조회 DTO
     */
    @Operation(summary = " 내 정보 조회 API ", description = "")
    @GetMapping("/myinfo")
    private ResponseEntity<MyInformationResponse> getMyInfo(HttpServletRequest request){

        MyInformationResponse myInformationResponse = mypageService.getMyInfo(request);

        return ResponseEntity.ok(myInformationResponse);
    }

    /* 내 프로필 수정 */




    /* 관리자에게 문의한 내용 리스트 조회 */
    /* 관리자에게  문의한 내용 상세 조회  */

}
