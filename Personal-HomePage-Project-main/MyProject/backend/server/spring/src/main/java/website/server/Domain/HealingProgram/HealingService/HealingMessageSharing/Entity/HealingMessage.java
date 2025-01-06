package website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.Entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HealingMessage {
    private Long messageId; // 게시물 고유 ID
    private Long userNumber; // 게시자 고유 번호
    private String title; // 게시물 제목
    private String content; // 게시물 본문
    private String imagePath; // 게시물 이미지 경로
    private LocalDateTime createdDate; // 작성 날짜
    private Integer likes; // 좋아요 수
}
