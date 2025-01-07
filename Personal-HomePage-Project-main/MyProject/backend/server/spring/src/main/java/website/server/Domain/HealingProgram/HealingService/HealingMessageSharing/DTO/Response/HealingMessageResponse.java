package website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Response;

import java.time.LocalDateTime;
import java.util.List;

public record HealingMessageResponse(

        String title,
        String profile_image_path,
        String nickname,
        LocalDateTime createdDate,
        String imagePath,
        String content
        //Long like,
        //List<String> comment

) {
}
