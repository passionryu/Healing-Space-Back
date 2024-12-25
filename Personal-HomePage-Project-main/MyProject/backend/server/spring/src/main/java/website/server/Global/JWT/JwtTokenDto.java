package website.server.Global.JWT;

public record JwtTokenDto(
        String accessToken,
        String refreshToken
) {
}
