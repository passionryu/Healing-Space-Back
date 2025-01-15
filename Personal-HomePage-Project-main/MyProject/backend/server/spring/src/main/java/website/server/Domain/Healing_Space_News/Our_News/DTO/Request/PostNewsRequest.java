package website.server.Domain.Healing_Space_News.Our_News.DTO.Request;

import org.springframework.web.multipart.MultipartFile;

public record PostNewsRequest(

        String title,
        String content,
        MultipartFile file // 이미지 파일을 MultipartFile로 변경
) {
}
