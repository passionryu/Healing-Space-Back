package website.server.Domain.HealingProgram.HealingService.DewCalendar.Util;

import java.util.List;
import java.util.Random;

public class HealingMusicList {

    private final static Random RANDOM = new Random();

    // 감정별 음악 리스트
    private final static List<String> joyMusic = List.of(
            "https://youtu.be/joy_music1",
            "https://youtu.be/joy_music2",
            "https://youtu.be/joy_music3",
            "https://youtu.be/joy_music4",
            "https://youtu.be/joy_music5"
    );

    private final static List<String> loveMusic = List.of(
            "https://youtu.be/love_music1",
            "https://youtu.be/love_music2",
            "https://youtu.be/love_music3",
            "https://youtu.be/love_music4",
            "https://youtu.be/love_music5"
    );

    private final static List<String> calmMusic = List.of(
            "https://youtu.be/calm_music1",
            "https://youtu.be/calm_music2",
            "https://youtu.be/calm_music3",
            "https://youtu.be/calm_music4",
            "https://youtu.be/calm_music5"
    );

    private final static List<String> lonelyMusic = List.of(
            "https://youtu.be/lonely_music1",
            "https://youtu.be/lonely_music2",
            "https://youtu.be/lonely_music3",
            "https://youtu.be/lonely_music4",
            "https://youtu.be/lonely_music5"
    );

    private final static List<String> sadMusic = List.of(
            "https://youtu.be/sad_music1",
            "https://youtu.be/sad_music2",
            "https://youtu.be/sad_music3",
            "https://youtu.be/sad_music4",
            "https://youtu.be/sad_music5"
    );

    private final static List<String> angryMusic = List.of(
            "https://youtu.be/angry_music1",
            "https://youtu.be/angry_music2",
            "https://youtu.be/angry_music3",
            "https://youtu.be/angry_music4",
            "https://youtu.be/angry_music5"
    );



    public static String getMusicByEmotion(String emotion) {
        return switch (emotion.toLowerCase()) {
            case "기쁨" -> getRandom(joyMusic);
            case "설렘&사랑" -> getRandom(loveMusic);
            case "평온" -> getRandom(calmMusic);
            case "외로움" -> getRandom(lonelyMusic);
            case "슬픔" -> getRandom(sadMusic);
            case "화남" -> getRandom(angryMusic);
            default -> throw new IllegalArgumentException("Unknown emotion: " + emotion);
        };
    }

    private static String getRandom(List<String> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }
}
