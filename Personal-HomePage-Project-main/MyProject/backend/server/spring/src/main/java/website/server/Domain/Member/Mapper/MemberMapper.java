package website.server.Domain.Member.Mapper;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import website.server.Domain.Member.Entity.Member;
import website.server.Domain.MyPage.DTO.Response.mypage.MyAllInformationResponse;
import website.server.Domain.MyPage.DTO.Response.mypage.MyInformationResponse;

@Mapper
public interface MemberMapper {

    /**
     * 회원 가입 메서드
     * @param member 멤버 객체
     */
    void register(Member member);

    /**
     * 이메일로 사용자 찾기
     * @param email 사용자 이메일
     * @return 멤버 객체
     */
    Member findMemberByEmail(String email);

    /**
     * 사용자 아이디(활동명)으로 사용자 찾기
     * @param nickname
     * @return
     */
    Member findMemberByNickname(String nickname);

    /**
     * 정보 조회로 아이디 찾기
     * @param username 사용자 본명
     * @param email 사용자 이메일
     * @return 사용자 아이디
     */
    String findID_option1(@Param("username") String username, @Param("email") String email);

    /**
     * 전화번호 인증으로 아이디 찾기 - 구현 보류
     * @return 사용자 아이디
     */
    String findID_option2();

    /**
     * 사용자 고유 번호로 프로필 정보 조회
     * @param userNumber 사용자 고유 번호
     * @return 프로필 정보 DTO(이미지 경로,사용자 ID, 유저 네임 )
     */
    MyInformationResponse getMyInfo(Long userNumber);

    /**
     * 사용자 고유 번호로 모든 정보 조회
     * @param userNumber 사용자 고유 번호
     * @return 사용자의 모든 정보 DTO
     */
    MyAllInformationResponse getMyInfoAll(Long userNumber);

    /**
     * 프로필 사진 수정
     * @param userNumber 사용자 고유 번호
     * @param filePath 프로필 사진 경로
     */
    void putProfileImage(@Param("userNumber") Long userNumber ,
                         @Param("filePath") String filePath);

    /**
     * 회원 탈퇴
     * @param userNumber 사용자 고유 번호
     */
    void delete(Long userNumber);

    //////////////
    /* Not Used */
    //////////////

    /**
     * 사용자 고유 번호로 프로필 정보 수정
     * @param userNumber 사용자 고유 번호
     */
    void changeMyInfo(@Param("userNumber") Long userNumber,
                      @Param("profileImagePath") String profileImagePath,
                      @Param("nickname")String nickname,
                      @Param("intro")String intro);

}
