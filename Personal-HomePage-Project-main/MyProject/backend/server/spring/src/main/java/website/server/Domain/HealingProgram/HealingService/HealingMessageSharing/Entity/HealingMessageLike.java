package website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.Entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HealingMessageLike {

    private Long likeId; // 좋아요 고유 ID
    private Long messageId; // 좋아요가 눌린 게시물 ID
    private Long userNumber; // 좋아요를 누른 사용자 ID
    private LocalDateTime createdDate; // 좋아요 날짜
}
