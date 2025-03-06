package website.server.Domain.Member.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.server.Domain.Member.DTO.Request.FindIDRequest_Option1;
import website.server.Domain.Member.DTO.Request.MemberRequest;
import website.server.Domain.Member.Service.MemberService;

@Slf4j
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
    public ResponseEntity<String> register(@RequestBody MemberRequest request) {

        log.info("MemberRequest {} :", request);
        Long createdID = memberService.register(request);

        return ResponseEntity.ok().body("회원 가입 성공! userID는" + createdID + "입니다.");
    }

    /**
     * 정보 조회로 아이디 찾기 API
     * @param request (username,email,password)가 들어있는 DTO
     * @return nickName 반환
     */
    @Operation(summary = "정보 조회로 아이디 찾기", description = "아이디 찾기 : 이메일,이름,비밀번호 입력")
    @PostMapping("/findID/option1")
    public ResponseEntity<String> findID_Option1(@RequestBody @Valid FindIDRequest_Option1 request){

        String nickname = memberService.findID_option1(request);

        return ResponseEntity.ok(nickname);
    }

    // TODO : Work Later....
    /* 전화번호 인증으로 아이디 찾기 */
    @Operation(summary = "전화번호 인증으로 아이디 찾기", description = "아이디 찾기 : 전화번호,이름 입력")
    @PostMapping("/findID/option2")
    public ResponseEntity<String> findID_Option2(){
        return null;
    }

    // TODO : Work Later....
    @Operation(summary = "비밀번호 분실 시 비밀번호 변경", description = "비밀번호 변경")
    @PostMapping("/changePW")
    public ResponseEntity<String> changePW(HttpServletRequest request){

        return null;
    }

}
