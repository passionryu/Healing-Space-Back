package website.server.Domain.MyPage.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import website.server.Domain.MyPage.DTO.Request.mypage.ChangeInformationRequest;
import website.server.Domain.MyPage.DTO.Response.mypage.MyAllInformationResponse;
import website.server.Domain.MyPage.DTO.Response.mypage.MyInformationResponse;
import website.server.Domain.MyPage.Service.MypageService.MypageService;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
@Tag(name = "MyPage", description = "마이페이지 서비스 API")
@CrossOrigin(origins = "http://localhost:5173") // React 앱이 실행되는 주소
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

        /* 내 정보 조회 */
        MyInformationResponse myInformationResponse = mypageService.getMyInfo(request);
        log.info("profile path : {}", myInformationResponse.ProfileImagePath());

        return ResponseEntity.ok(myInformationResponse);
    }

    /**
     * 내 모든 정보 조회 API
     * @param request 사용자 요청
     * @return 모든 정보 조회
     */
    @Operation(summary = "내 모든 정보 조회 API", description = "")
    @GetMapping("/myinfo/all")
    private ResponseEntity<MyAllInformationResponse> getMyInfoAll(HttpServletRequest request){

        MyAllInformationResponse myAllInformationResponse = mypageService.getMyInfoAll(request);

        return ResponseEntity.ok(myAllInformationResponse);
    }

    /**
     * 프로필 이미지 수정 API
     * @param request 사용자 요청
     * @param file 요청 파일
     * @return 프로필 이미지 수정 성공 메시지
     */
    @Operation(summary = "프로필 이미지 수정 API" , description = "")
    @PutMapping(value = "/profile/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> putProfileImage(HttpServletRequest request,
                                                  @RequestParam("file") MultipartFile file) throws IOException {

        /* 프로필 이미지 수정 */
        String filePath  = mypageService.putProfileImage(request,file);

        return ResponseEntity.ok("프로필 이미지 수정 경로 = " + filePath);
    }

    /**
     * 아이디 수정 API
     * @param request 사용자 요청
     * @param id 수정하고자 하는 id 이름
     * @return 프로필 아이디 수정 성공 메시지
     */
    @Operation(summary = "아이디 수정 API", description = "")
    @PutMapping("/profile/id/{id}")
    public ResponseEntity<String> putProfileId(HttpServletRequest request,
                                               @PathVariable("id") Long id){

        return null;
    }

    /* 인트로 수정 */

    //////////////
    /* Not Used */
    //////////////

    /**
     * 내 프로필 수정 API
     * @param request 사용자 요청
     * @return 수정된 프로필 DTO
     */
    @Operation(summary = " 내 프로필 수정 API ", description = "")
    @PatchMapping("/myinfo")
    private ResponseEntity<MyInformationResponse> changeInfo(HttpServletRequest request,
                                                             @RequestBody ChangeInformationRequest changeInformationRequest){

        /* 프로필 수정 API */
        MyInformationResponse myInformationResponse = mypageService.changeInfo(request,changeInformationRequest);

        return ResponseEntity.ok(myInformationResponse);
    }


    /* 관리자에게 문의한 내용 리스트 조회 */
    /* 관리자에게  문의한 내용 상세 조회  */

}
