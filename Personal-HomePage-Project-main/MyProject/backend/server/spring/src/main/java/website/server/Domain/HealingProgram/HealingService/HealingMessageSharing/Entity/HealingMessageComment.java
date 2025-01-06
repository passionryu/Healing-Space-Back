package website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.Entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HealingMessageComment {
    private Long commentId; // 댓글 고유 ID
    private Long messageId; // 댓글이 달린 게시물 ID
    private Long userNumber; // 댓글 작성자 ID
    private String content; // 댓글 내용
    private LocalDateTime createdDate; // 작성 날짜
}
