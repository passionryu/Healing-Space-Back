package website.server.Domain.MyPage.DTO.Request.mypage;

public record ChangeInformationRequest(
        String ProfileImagePath,
        String nickName,
        String intro
) {
}
