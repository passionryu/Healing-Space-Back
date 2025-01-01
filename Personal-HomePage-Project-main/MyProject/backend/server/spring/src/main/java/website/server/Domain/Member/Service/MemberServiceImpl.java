package website.server.Domain.Member.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import website.server.Domain.Member.DTO.Request.FindIDRequest_Option1;
import website.server.Domain.Member.DTO.Request.MemberRequest;
import website.server.Domain.Member.Entity.Member;
import website.server.Domain.Member.Mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 회원가입 메서드
     * @param request 회원가입 정보 객체
     * @return 사용자 고유번호 반환
     */
    @Override
    public Long register(MemberRequest request) {

        Member member = request.toEntity();

        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        memberMapper.register(member);

        return member.getUserNumber();
    }

    /**
     * 정보 조회로 아이디 찾기 메서드
     * @param request (username,email,password)가 들어있는 DTO
     * @return nickName 반환
     */
    @Override
    public String findID_option1(FindIDRequest_Option1 request) {

        String username = request.username();
        String email = request.email();

        // PW validation
        Member member = memberMapper.findMemberByEmail(email);
        String password = request.password();
        if (!passwordEncoder.matches(password, member.getPassword()))
         return "Not matched PW";

        return memberMapper.findID_option1(username,email);
    }

    @Override
    public String findID_option2() {
        return null;
    }

    @Override
    public String changePW() {
        return "";
    }
}
