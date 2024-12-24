package website.server.Domain.Member.Service;

import website.server.Domain.Member.DTO.Request.MemberRequest;

public interface MemberService {

    /**
     * 회원 가입 인터페이스
     * @param request 회원가입 정보 객체
     * @return 회원가입 후 생성된 ID
     */
    Long register(MemberRequest request);

    /**
     * 아이디 찾기 인터페이스
     * @return 아이디
     */
    Long findID();

    /**
     * 비밀번호 변경 인터페이스
     * @return
     */
    String changePW();

}
