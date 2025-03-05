package website.server.Global.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /**
     * 회원 도메인 에러 - 6xx
     * 회원가입 에러 - 61x
     * 로그인 에러 - 62x
     */
    NOT_FOUND_MEMBER(621,"Member DB에서 해당 유저를 조회할 수 없습니다."),
    PW_NOT_MATCH(622,"비밀번호가 일치하지 않습니다.");


    private final int errorCode;
    private final String message;

}
