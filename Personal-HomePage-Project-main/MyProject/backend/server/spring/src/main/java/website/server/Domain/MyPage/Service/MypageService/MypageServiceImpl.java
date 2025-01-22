package website.server.Domain.MyPage.Service.MypageService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import website.server.Domain.Member.Mapper.MemberMapper;
import website.server.Domain.MyPage.DTO.Request.mypage.ChangeInformationRequest;
import website.server.Domain.MyPage.DTO.Response.mypage.MyInformationResponse;
import website.server.Global.JWT.JwtService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService{

    private final MemberMapper memberMapper;
    private final JwtService jwtService;

    //    @Value("${profile.file.storage.path}")
    //    private String storagePath;
    private final Path storagePath = Paths.get("C:/Users/rsy/Desktop/Personal-HomePage-Project-main/Personal-HomePage-Project-main/MyProject/backend/server/spring/src/main/resources/static/images/Profile");
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
     * 프로필 이미지 수정 메서드
     * @param request 사용자 요청
     * @param file 요청 이미지
     */
    @Override
    public String putProfileImage(HttpServletRequest request, MultipartFile file) throws IOException {

        log.info("Storage Path: {}", storagePath);

        /* 사용자 고유번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);
        log.info("userNumber : {}" ,  userNumber);

        // 파일 저장
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        log.info("fileName : {} ", fileName);

        String filePath = storagePath + "/" + fileName;
        log.info("filePath : {}",filePath);

        // 디렉토리 생성
        File directory = new File(String.valueOf(storagePath));
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 파일 저장
        file.transferTo(new File(filePath));

        memberMapper.putProfileImage(userNumber,fileName);

        return filePath;

    }

    //////////////
    /* Not Used */
    //////////////

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
