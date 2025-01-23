package website.server.Domain.HealingProgram.AiService.AiSnapShot.Util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 레디스에 데이터 임시 저장 메서드
     * @param nickName 유저 닉네임
     * @param questionNumber 질문 번호
     * @param answer 사용자 답변
     */
    public void saveAnswerToRedis(String nickName, int questionNumber, String answer){

        redisTemplate.opsForValue().set("Answer "+ questionNumber +" from "+ nickName , answer);
        log.info("Redis에 다음과 같이 저장되었습니다 - "
                + nickName +" 님의 "
                + questionNumber + "번 답변 : "
                + answer);
    }

    /**
     * 레디스에서 해당 사용자의 모든 답변 반환 메서드
     * @param nickName 답변을 찾을 사용자 닉네임
     * @return 사용자의 답변 배열 반환 - size:7
     */
    public String[] getAnswerFromRedis(String nickName){

        int questionNumber;
        String[] answerArray = new String[7];

        for (int i = 0; i < 7; i++) {
            questionNumber = i + 1; // questionNumber는 1부터 시작
            String key = "Answer " + questionNumber + " from " + nickName;

            /* Redis에서 모든 답변 반환 */
            answerArray[i] = redisTemplate.opsForValue().get(key);
        }

        /* 로그 확인 */
        log.info(Arrays.toString(answerArray));

        return answerArray;
    }

    /**
     * 레디스에서 해당 유저의 모든 답변 삭제 메서드
     * @param nickName 유저 닉네임
     */
    public void deleteAnswerFromRedis(String nickName){

        int questionNumber;

        for (int i = 0; i < 7; i++) {
            questionNumber = i + 1; // questionNumber는 1부터 시작
            redisTemplate.delete( "Answer "+ questionNumber +" from "+ nickName );

            log.info( nickName +" 님의 "
                    + questionNumber + "번 데이터가 Redis에서 삭제 되었습니다. ");
        }
    }


}
