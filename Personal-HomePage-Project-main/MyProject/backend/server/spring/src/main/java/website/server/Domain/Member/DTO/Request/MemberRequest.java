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

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password


){
        public Member toEntity() {
        return Member.builder()
                .username(this.username)
                .nickName(this.nickName)
                .phone_number(this.phoneNumber)
                .password(this.password)
                .build();
}}
