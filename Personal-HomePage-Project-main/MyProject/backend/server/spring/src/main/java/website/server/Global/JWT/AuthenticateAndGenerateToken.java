package website.server.Global.JWT;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import website.server.Domain.Member.Mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class AuthenticateAndGenerateToken {

    private final MemberMapper memberMapper;

    public JwtTokenDto authenticateAndGenerateToken(String email, String password) {

//        // 저장소에서 email로 사용자를 찾고
//        Member member = memberMapper.findMemberByEmail(email)
//                .filter(member -> passwordEncoder.matches(password, member.getPassword()))
//                .map
//        // 비밀번호를 비교
//        // 사용자를 통해 엑세스 토큰 생성
//        // 사용자를 통해 리프레시 토큰 생성
//        // DTO에 담기
//        // 예외처리
//
//        // DTO 반환
          return null;

    }
}

        /*
        *  return memberRepository.findByUsername(username)
                .filter(member -> passwordEncoder.matches(password, member.getPassword()))
                .map(member -> {
                    String accessToken = jwtUtil.generateAccessToken(username,member.getEmail(),member.getRole());
                    String refreshToken = jwtUtil.generateRefreshToken(username);
                    return new JwtTokenDto(accessToken, refreshToken);
                })
                .orElseThrow(() -> new MemberException(ErrorCode.FAILED_TO_LOGIN));
            */