package website.server.Domain.Member.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data //getter,setter,tostring,EqualsAndHashCode,RequiredArgsConstructor 포함
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    /* 추가 가능한 필드 */
    // 1.프로필 사진 - 추가 완료 : 20250104
    // 2.거주지 주소
    // 3.활동 상태 : 활성화,비활성화 ,정지상태

    /* 회원가입 시 자동 저장 정보 */
    private Long userNumber; // 사용자 고유 번호
    private String role;
    private LocalDateTime registerDate;

    /* 회원가입 시 사용자 직접 입력 정보  */
    private String username; // 실제 사용자 이름
    private String nickName; // 아이디(활동명)
    private String phoneNumber;
    private String gender;
    private String email;
    private String password;
    private LocalDate birth;

    /**
     * 프로필 조회 데이터
     * 1. 사진 경로
     * 2. 사용자 ID
     * 3. 짧은 자기 소개
     */
    private String profileImagePath;
    private String intro;

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
