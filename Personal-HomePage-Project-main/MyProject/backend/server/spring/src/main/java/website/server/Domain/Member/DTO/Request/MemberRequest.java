package website.server.Domain.Member.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import website.server.Domain.Member.Entity.Member;
import java.time.LocalDate;

@Builder
public record MemberRequest(

        @NotBlank(message = "Username is required")
        String username,

        String nickName,
        String phoneNumber,
        String gender,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        //생일에 대한 유효성 검사는 notblank(문자열)가 될 수 없다.
        @NotNull
        LocalDate birth
){
        public Member toEntity() {
        return Member.builder()
                .username(this.username)
                .nickname(this.nickName)
                .phoneNumber(this.phoneNumber)
                .gender(this.gender)
                .password(this.password)
                .email(this.email)
                .birth(this.birth)
                .build();
}}
