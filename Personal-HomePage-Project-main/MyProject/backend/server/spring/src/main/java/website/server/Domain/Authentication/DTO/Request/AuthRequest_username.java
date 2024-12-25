package website.server.Domain.Authentication.DTO.Request;

// 사용자 이름으로 로그인 처리
public record AuthRequest_username(
        String username,
        String password
) {
}
