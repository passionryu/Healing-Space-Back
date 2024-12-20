package website.server.Domain.Member.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data //getter,setter,tostring,EqualsAndHashCode,RequiredArgsConstructor 포함
@Builder
@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 포함한 생성자 생성
public class Member {

    private Long userID;
    //private Role role = Role.USER;
    private String role; // MyBatis에서는 Enum을 String으로 처리 (ENUM -> String 매핑)
    private LocalDateTime registerDate;
    private String username;
    private String email;
    private String password;
    private LocalDate birth;

}

