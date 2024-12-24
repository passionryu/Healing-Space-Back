package website.server.Domain.Member.Mapper;

import org.apache.ibatis.annotations.Mapper;
import website.server.Domain.Member.Entity.Member;

@Mapper
public interface MemberMapper {

    /* 회원가입 */
    void register(Member member);

    /* 이메일로 사용자 찾기 */
    Member findMemberByEmail(String email);

    /* 사용자 아이디(활동명)으로 사용자 찾기 */
    Member findMemberByNickname(String nickname);

}
