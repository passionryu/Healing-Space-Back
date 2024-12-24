package website.server.Domain.Member.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import website.server.Domain.Member.Entity.Member;

@Mapper
public interface MemberMapper {

    /* 회원가입 */
    void register(Member member);

    /* 이메일로 사용자 찾기 */
    Member findMemberByEmail(String email);

    /* 사용자 아이디(활동명)으로 사용자 찾기 */
    Member findMemberByNickname(String nickname);

    /* 정보 조회로 아이디 찾기 */
    String findID_option1(@Param("username") String username, @Param("email") String email);

    /* 전화번호 인증으로 아이디 찾기 */
    String findID_option2();
}
