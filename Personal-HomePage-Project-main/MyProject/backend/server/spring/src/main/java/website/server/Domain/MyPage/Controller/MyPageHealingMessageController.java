package website.server.Domain.MyPage.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
@Tag(name = "MyPage", description = "마이페이지 서비스 API")
public class MyPageHealingMessageController {

    /* 내가 올린 힐링 메시지 리스트 조회*/
    /* 내가 올린 힐링 메시지 상세 조회*/
    /* 내가 올린 힐링 메시지 삭제*/

}
