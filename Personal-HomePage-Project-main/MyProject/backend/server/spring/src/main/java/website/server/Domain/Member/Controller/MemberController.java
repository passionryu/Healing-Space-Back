package website.server.Domain.Member.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import website.server.Domain.Member.DTO.Request.MemberRequest;
import website.server.Domain.Member.Service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Tag(name = "Member", description = "회원 시스템 API")
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입 API
     * @param request 회원가입 정보 객체
     * @return 회원가입 성공 메시지 + 회원가입 후 생성된 ID
     */
    @Operation(summary = " 회원가입 ", description = " 회원가입 : username,email,password,birth")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid MemberRequest request) {

        Long createdID = memberService.register(request);

        return ResponseEntity.ok().body("회원 가입 성공! userID는" + createdID + "입니다.");
    }

    @Operation(summary = "아이디 찾기", description = "아이디 찾기")
    @PostMapping("/findID")
    public ResponseEntity<String> findID(HttpServletRequest request){

        return null;
    }

    @Operation(summary = "비밀번호 변경", description = "비밀번호 변경")
    @PostMapping("/changePW")
    public ResponseEntity<String> changePW(HttpServletRequest request){

        return null;
    }

}
