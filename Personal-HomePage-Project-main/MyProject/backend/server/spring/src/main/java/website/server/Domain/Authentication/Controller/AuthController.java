package website.server.Domain.Authentication.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.server.Domain.Authentication.DTO.Request.AuthRequest_email;
import website.server.Domain.Authentication.DTO.Request.AuthRequest_id;
import website.server.Global.JWT.JwtTokenDto;
import website.server.Domain.Authentication.Service.AuthService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Auth", description = "인증 서비스 API")
//@CrossOrigin(origins = "http://healing-space-front.s3-website.ap-northeast-2.amazonaws.com")
public class AuthController {

    private final AuthService authService;

    /**
     * 이메일 로그인 API
     * @param request 로그인 DTO (이메일,비밀번호)
     * @return 200 ok + JWT 토큰
     */
    @Operation(summary = " 이메일 로그인 API ", description = " 로그인 : email,password")
    @PostMapping("/login/email")
    public ResponseEntity<JwtTokenDto> email_Login(@RequestBody AuthRequest_email request){

        JwtTokenDto jwtToken = authService.email_Login(request.email(), request.password());
        if (jwtToken == null)
            return null;

        return ResponseEntity.ok(jwtToken);
    }

    /**
     * 아이디 로그인 API
     * @param request 로그인 DTO(ID,비밀번호)
     * @return 200 ok + JWT 토큰
     */
    @Operation(summary = "아이디 로그인 API" , description = "로그인 : ID,password")
    @PostMapping("/login/id")
    public ResponseEntity<JwtTokenDto> nickName_Login(@RequestBody AuthRequest_id request){

        JwtTokenDto jwtToken = authService.id_Login(request.nickName(), request.password());
        if (jwtToken == null)
            return null;

        return ResponseEntity.ok(jwtToken);
    }

    /**
     * 로그아웃 API
     * @param request 사용자 로그아웃 요청
     * @return 로그아웃 메시지
     */
    @Operation(summary = "로그 아웃 API" ,description = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){

        authService.logout(request);

        return ResponseEntity.ok("Logout Success ! Your AccessToken is updated at blacklist ");
    }


    /**
     * 회원 탈퇴 API
     * @param request 사용자 요청
     * @return 회원 탈퇴 메시지
     */
    @Operation(summary = "회원 탈퇴 API",description = "회원 탈퇴")
    @PostMapping("/delete")
    public ResponseEntity<String> delete(HttpServletRequest request){

        authService.delete(request);

        return ResponseEntity.ok("Member Delete Success");
    }

    // TODO : work later ...
    @Operation(summary = "소셜 로그인 API",description = "소셜 로그인")
    @PostMapping("/login/social")
    public ResponseEntity<String> social_login(){

        return ResponseEntity.ok("Success Social Login");
    }


}
