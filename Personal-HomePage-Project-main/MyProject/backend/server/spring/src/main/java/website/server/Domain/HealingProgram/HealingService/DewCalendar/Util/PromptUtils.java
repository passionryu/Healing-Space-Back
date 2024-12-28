package website.server.Domain.HealingProgram.HealingService.DewCalendar.Util;

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
                +" 이 일기를 보고,글쓴이의 감정을 다음 8가지 감정 중 하나로 분석해줘. "
                +" 1.기쁨\n" +
                        "2.설렘&사랑\n" +
                        "3.평온\n" +
                        "4.외로움\n" +
                        "5.피곤함 \n" +
                        "6.슬픔\n" +
                        "7.화남\n" +
                        "8.좌절 ";

        return emotion;
    }


}

