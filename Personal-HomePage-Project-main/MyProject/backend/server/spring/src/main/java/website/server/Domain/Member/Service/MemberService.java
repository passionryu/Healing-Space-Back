package website.server.Domain.Member.Service;

import website.server.Domain.Member.DTO.Request.MemberRequest;

public interface MemberService {

    /* 회원 가입 */
    Long register(MemberRequest request);

    /* 아이디 찾기 */
    Long findID();

    /* 비밀 번호 변경 */
    String changePW();

}
