package website.server.Domain.CustomerService.Contact_Us.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/contactus")
@Tag(name = "Contact Us", description = "Contact Us API")
public class ContactUsController {

    @Operation(summary = "", description = "")
    @PostMapping()
    public ResponseEntity<String> sendEmail(HttpServletRequest request){

        return ResponseEntity.ok("이메일 전송 성공");
    }

}
