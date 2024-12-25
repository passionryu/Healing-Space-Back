package website.server.Domain.Authentication.DTO.Request;

// 이메일로 로그인 처리
public record AuthRequest_email(
        String email,
        String password
) {
}
