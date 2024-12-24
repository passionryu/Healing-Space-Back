package website.server.Domain.Member.Service;

import website.server.Domain.Member.DTO.Request.FindIDRequest_Option1;
import website.server.Domain.Member.DTO.Request.MemberRequest;

public interface MemberService {

    /**
     * 회원 가입 인터페이스
     * @param request 회원가입 정보 객체
     * @return 회원가입 후 생성된 ID
     */
    Long register(MemberRequest request);

    /**
     * 정보 조회로 아이디 찾기 인터페이스
     * @return 아이디
     */
    String findID_option1(FindIDRequest_Option1 request);

    /**
     * 전화번호 인증으로 아이디 찾기 인터페이스
     * @return
     */
    String findID_option2();

    /**
     * 비밀번호 변경 인터페이스
     * @return
     */
    String changePW();

}
