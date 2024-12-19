package website.server.Member.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import website.server.Member.Role.Role;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data //getter,setter,tostring,EqualsAndHashCode,RequiredArgsConstructor 포함
@Entity
@Builder
@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 포함한 생성자 생성
public class Member {

    /* 회원 가입 = 자동 지정 정보 + 사용자 입력 정보 */

    /**
     * 자동 지정 정보
     * 1. 사용자 고유 번호
     * 2. 역할(디폴트=사용자)
     * 3. 회원 가입 날짜
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(updatable = false)
    private LocalDateTime registerDate;

    /**
     * 사용자 입력 정보
     * 1. 사용자 이름
     * 2. 이메일
     * 3. 비밀 번호
     * 4. 생일
     */
    private String username;
    private String email;
    private String password;
    private LocalDate birth;

    /**
     * 엔티티가 저장되기 전에 실행되는 메서드
     * 1. 등록일 = 현재시간
     * 2. Role = user
     */
    @PrePersist
    protected void onCreate() {
        this.registerDate = LocalDateTime.now();
        this.role = Role.USER;
    }

    //good
}

