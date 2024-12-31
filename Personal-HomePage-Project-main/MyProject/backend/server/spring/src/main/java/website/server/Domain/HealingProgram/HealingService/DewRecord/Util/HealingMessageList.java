package website.server.Domain.HealingProgram.HealingService.DewRecord.Util;

import java.util.List;
import java.util.Random;

public class HealingMessageList {

    private final static Random RANDOM = new Random();

    // 감정별 글귀 리스트
    private final static List<String> joyMessages = List.of(
            "오늘 하루는 빛나는 별처럼 아름답습니다.",
            "웃음이 당신의 하루를 채우길 바랍니다.",
            "행복은 선택입니다. 오늘도 당신을 응원합니다.",
            "당신의 미소가 세상을 밝게 만듭니다.",
            "기쁨은 작은 순간들에 숨어 있습니다."
    );

    private final static List<String> loveMessages = List.of(
            "사랑은 당신 안에 있습니다. 스스로를 사랑하세요.",
            "설렘은 삶을 더 가치 있게 만듭니다.",
            "오늘도 누군가의 특별한 사람이 되어주세요.",
            "사랑은 힘입니다. 당신은 사랑받을 자격이 있습니다.",
            "작은 친절이 큰 사랑이 됩니다."
    );

    private final static List<String> calmMessages = List.of(
            "고요한 마음이 당신을 채우길 바랍니다.",
            "삶의 작은 순간에 감사하세요.",
            "평온함은 내면에서 시작됩니다.",
            "바람 소리에 귀를 기울여 보세요.",
            "오늘도 자신을 믿으세요."
    );

    private final static List<String> lonelyMessages = List.of(
            "당신은 혼자가 아닙니다.",
            "누군가는 당신을 생각하고 있습니다.",
            "혼자 있는 시간이 때로는 성장의 시간입니다.",
            "곁에 있는 작은 기쁨을 찾아보세요.",
            "외로움은 지나가는 구름과 같습니다."
    );


    private final static List<String> sadMessages = List.of(
            "눈물은 마음을 치유합니다.",
            "슬픔은 지나가는 한 순간입니다.",
            "어두운 날에도 희망은 있습니다.",
            "당신의 이야기를 들어줄 사람이 있습니다.",
            "혼자가 아님을 기억하세요."
    );

    private final static List<String> angryMessages = List.of(
            "분노는 이해의 시작점이 될 수 있습니다.",
            "숨을 깊게 들이쉬고 내쉬어 보세요.",
            "감정을 억누르기보다는 인정하세요.",
            "화를 가라앉히는 시간을 가져보세요.",
            "당신은 이 순간을 이겨낼 수 있습니다."
    );


    public static String getMessageByEmotion(String emotion) {
        return switch (emotion.toLowerCase()) {
            case "기쁨" -> getRandom(joyMessages);
            case "설렘&사랑" -> getRandom(loveMessages);
            case "평온" -> getRandom(calmMessages);
            case "외로움" -> getRandom(lonelyMessages);
            case "슬픔" -> getRandom(sadMessages);
            case "화남" -> getRandom(angryMessages);
            default -> throw new IllegalArgumentException("Unknown emotion: " + emotion);
        };
    }

    private static String getRandom(List<String> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }
}
