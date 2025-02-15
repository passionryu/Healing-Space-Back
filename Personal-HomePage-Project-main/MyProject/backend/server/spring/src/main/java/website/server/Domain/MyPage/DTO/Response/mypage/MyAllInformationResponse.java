package website.server.Domain.MyPage.DTO.Response.mypage;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MyAllInformationResponse(
        String profile_image_path,
        String Id,
        String name,
        String phoneNumber,
        LocalDate birth,
        String email,
        LocalDateTime registerDate
) {
}
