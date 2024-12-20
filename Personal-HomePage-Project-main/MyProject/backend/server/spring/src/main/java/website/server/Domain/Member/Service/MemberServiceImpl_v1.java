package website.server.Domain.Member.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import website.server.Domain.Member.DTO.Request.MemberRequest;
import website.server.Domain.Member.Entity.Member;
import website.server.Domain.Member.Mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl_v1 implements MemberService{

    private final MemberMapper memberMapper;

    @Override
    public Long register(MemberRequest request) {

        Member member = request.toEntity();
        memberMapper.register(member);

        return member.getUserID();
    }

    @Override
    public Long findID() {
        return null;
    }

    @Override
    public String changePW() {
        return "";
    }
}
