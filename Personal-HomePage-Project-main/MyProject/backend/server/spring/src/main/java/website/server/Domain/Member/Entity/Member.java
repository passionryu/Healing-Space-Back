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

    // 활동명, 닉네임
    // 전화번호
    // 성별
    // 프로필 사진
    // 거주지 주소
    // 활동 상태 : 활성화, 비활성화 , 정지상태

    private Long userID;
    //private Role role = Role.USER;
    private String role; // MyBatis에서는 Enum을 String으로 처리 (ENUM -> String 매핑)
    private LocalDateTime registerDate;
    private String username;
    private String email;
    private String password;
    private LocalDate birth;


    //비밀번호 인코딩 함수
    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}

