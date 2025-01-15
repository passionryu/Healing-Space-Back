package website.server.Domain.Healing_Space_News.Our_News.Util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class OurNews_Set_Image_Path {

    /* 사진 저장 선택지 1 : 기본 저장 경로 (운영 체제 독립적으로 작성) */
    //private final Path uploadDir = Paths.get(System.getProperty("user.home"), "uploads", "images");

    /* 사진 저장 선택지 2 : 서버  */
    private final Path uploadDir = Paths.get("C:\\Users\\류성열\\Desktop\\Personal-HomePage-Project-main\\Personal-HomePage-Project-main\\MyProject\\backend\\server\\spring\\src\\main\\resources\\images\\OurNews");

    public OurNews_Set_Image_Path() {
        // 디렉토리가 존재하지 않으면 생성
        try {
            if (!Files.exists(uploadDir)){
                Files.createDirectories(uploadDir);
            }
        } catch (IOException e) {
            throw new RuntimeException(" Error Location = (website.server.Domain.Healing_Space_News.Our_News.Util.OurNews_Set_Image_Path) - " +
                    " Failed to create upload directory: " + e.getMessage(), e);
        }
    }

    public String uploadPhoto(MultipartFile file) throws IOException {

            // 파일 이름 중복 방지를 위해 UUID 사용
            String originalFilename = file.getOriginalFilename();
            String uniqueFilename = UUID.randomUUID() + "_" + originalFilename;

            // 저장할 파일의 경로
            Path destinationPath = uploadDir.resolve(uniqueFilename);

            // 파일 저장
            file.transferTo(destinationPath.toFile());

            // 저장된 파일 경로 반환
            return destinationPath.toString();

    }
}
