package website.server.Domain.HealingProgram.HealingService.DewRecord.Util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PromptUtils {

    /**
     * 다이어리 리퀘스트 프롬프트 메시지 생성 메서드
     * @param title 일기 제목
     * @param diary 일기 본문
     * @return 제목과 본문을 합친 프롬프트 메시지
     */
    public static String writeDiaryRequest(String title,String diary){

        String fullDiary =
                "위 일기의 제목은 "
                + title + "이고,"
                +"위 일기의 본문은 "
                +diary +"이다.";

        String emotion =
                fullDiary
                +" 이 일기를 보고,글쓴이의 감정을 다음 86지 감정 중 하나로 분석해줘. "
                +" 1.기쁨\n" +
                        "2.설렘&사랑\n" +
                        "3.평온\n" +
                        "4.외로움\n" +
                        "5.슬픔\n" +
                        "6.화남\n";

        return emotion;
    }

    public static String createHealingMessagePrompt(String diary){

        String promptMessage =
                "너는 존댓말을 사용하는 친근하고 따뜻한 친구이다. 다음은 사용자의 일기이다." +
                "이 일기를 보고 사용자에게 위로와 격려의 메시지를 10~15줄 써줘라 -> " + diary;

        return promptMessage;
    }


}

