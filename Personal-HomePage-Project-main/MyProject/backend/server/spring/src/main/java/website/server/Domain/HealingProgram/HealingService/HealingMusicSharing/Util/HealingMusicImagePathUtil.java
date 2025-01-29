//package website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Util;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.UUID;
//
//@Component
//public class HealingMusicImagePathUtil {
//
//    /* 사진 저장 장소 - Local 기준 */
//    private final Path uploadDir = Paths.get("C:/Users/rsy/Desktop/Personal-HomePage-Project-main/Personal-HomePage-Project-main/MyProject/backend/server/spring/src/main/resources/static/images/HealingService/HealingMusic");
//
//    public void uploadPhoto(String originalFilename) throws IOException {
//
//        // 파일 이름 중복 방지를 위해 UUID 사용
//        //String originalFilename = file.getOriginalFilename();
//        String uniqueFilename = UUID.randomUUID() + "_" + originalFilename;
//
//        // 저장할 파일의 경로
//        Path destinationPath = uploadDir.resolve(uniqueFilename);
//
//
//    }
//
//}
