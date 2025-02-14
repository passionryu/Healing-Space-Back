package website.server.Domain.HealingProgram.HealingService.DewRecord.Util;

import java.util.List;
import java.util.Random;

public class HealingMusicList {

    private final static Random RANDOM = new Random();

    // 감정별 음악 리스트
    private final static List<String> joyMusic = List.of(
            "https://www.youtube.com/watch?v=ZbZSe6N_BXs", // Happy - Pharrell Williams
            "https://www.youtube.com/watch?v=OPf0YbXqDm0", // Uptown Funk - Mark Ronson ft. Bruno Mars
            "https://www.youtube.com/watch?v=ru0K8uYEZWw", // Can't Stop The Feeling! - Justin Timberlake
            "https://www.youtube.com/watch?v=09R8_2nJtjg", // Sugar - Maroon 5
            "https://www.youtube.com/watch?v=iPUmE-tne5U", // Walking on Sunshine - Katrina & The Waves
            "https://www.youtube.com/watch?v=fRh_vgS2dFE", // Sorry - Justin Bieber
            "https://www.youtube.com/watch?v=nfWlot6h_JM", // Shake It Off - Taylor Swift
            "https://www.youtube.com/watch?v=JGwWNGJdvx8", // Shape of You - Ed Sheeran
            "https://www.youtube.com/watch?v=PT2_F-1esPk", // Closer - The Chainsmokers ft. Halsey
            "https://www.youtube.com/watch?v=UqyT8IEBkvY", // 24K Magic - Bruno Mars
            "https://www.youtube.com/watch?v=oygrmJFKYZY", // Don't Start Now - Dua Lipa
            "https://www.youtube.com/watch?v=pRpeEdMmmQ0", // Waka Waka - Shakira
            "https://www.youtube.com/watch?v=CevxZvSJLk8", // Roar - Katy Perry
            "https://www.youtube.com/watch?v=hT_nvWreIhg", // Counting Stars - OneRepublic
            "https://www.youtube.com/watch?v=iS1g8G_njx8", // Problem - Ariana Grande ft. Iggy Azalea
            "https://www.youtube.com/watch?v=7PCkvCPvDXk", // All About That Bass - Meghan Trainor
            "https://www.youtube.com/watch?v=6JCLY0Rlx6Q", // Shut Up and Dance - Walk The Moon
            "https://www.youtube.com/watch?v=YBHQbu5rbdQ", // Worth It - Fifth Harmony ft. Kid Ink
            "https://www.youtube.com/watch?v=IcrbM1l_BoI", // Wake Me Up - Avicii
            "https://www.youtube.com/watch?v=KQ6zr6kCPj8"  // Party Rock Anthem - LMFAO ft. Lauren Bennett, GoonRock
    );
    private final static List<String> loveMusic = List.of(
            "https://www.youtube.com/watch?v=450p7goxZqg", // Perfect - Ed Sheeran
            "https://www.youtube.com/watch?v=09R8_2nJtjg", // Sugar - Maroon 5
            "https://www.youtube.com/watch?v=JGwWNGJdvx8", // Shape of You - Ed Sheeran
            "https://www.youtube.com/watch?v=450p7goxZqg", // Thinking Out Loud - Ed Sheeran
            "https://www.youtube.com/watch?v=RBumgq5yVrA", // Say You Won't Let Go - James Arthur
            "https://www.youtube.com/watch?v=lp-EO5I60KA", // All of Me - John Legend
            "https://www.youtube.com/watch?v=YQHsXMglC9A", // Hello - Adele
            "https://www.youtube.com/watch?v=0go2nfVXFgA", // Love Me Like You Do - Ellie Goulding
            "https://www.youtube.com/watch?v=1UQzJfsT2eo", // I Don't Want to Miss a Thing - Aerosmith
            "https://www.youtube.com/watch?v=ZJL4UGSbeFg", // You Belong With Me - Taylor Swift

            "https://www.youtube.com/watch?v=LjhCEhWiKXk", // Just The Way You Are - Bruno Mars
            "https://www.youtube.com/watch?v=KqFsTgt3o7U", // You Are The Reason - Calum Scott
            "https://www.youtube.com/watch?v=V3N5CsXYlOY", // Lucky - Jason Mraz & Colbie Caillat
            "https://www.youtube.com/watch?v=450p7goxZqg", // Everything - Michael Bublé
            "https://www.youtube.com/watch?v=0put0_a--Ng", // Make You Feel My Love - Adele
            "https://www.youtube.com/watch?v=tcYodQoapMg", // I Choose You - Sara Bareilles
            "https://www.youtube.com/watch?v=FkHuoHLeZqA", // Better Together - Jack Johnson
            "https://www.youtube.com/watch?v=dvgZkm1xWPE", // With or Without You - U2
            "https://www.youtube.com/watch?v=450p7goxZqg"  // Endless Love - Lionel Richie & Diana Ross
    );

    private final static List<String> calmMusic = List.of(
            "https://www.youtube.com/watch?v=UfcAVejslrU", // Weightless - Marconi Union
            "https://www.youtube.com/watch?v=CvFH_6DNRCY", // Clair de Lune - Claude Debussy
            "https://www.youtube.com/watch?v=S-Xm7s9eGxU", // Gymnopédie No.1 - Erik Satie
            "https://www.youtube.com/watch?v=7maJOI3QMu0", // River Flows in You - Yiruma
            "https://www.youtube.com/watch?v=1JHj1Ad30Xk", // Experience - Ludovico Einaudi
            "https://www.youtube.com/watch?v=kcihcYEOeic", // Nuvole Bianche - Ludovico Einaudi
            "https://www.youtube.com/watch?v=rtOvBOTyX00", // A Thousand Years - Christina Perri (Piano)
            "https://www.youtube.com/watch?v=NlprozGcs80", // Canon in D - Pachelbel
            "https://www.youtube.com/watch?v=TJ6Mzvh3XCc", // Spiegel im Spiegel - Arvo Pärt
            "https://www.youtube.com/watch?v=4zLfCnGVeL4", // The Sound of Silence - Simon & Garfunkel
            "https://www.youtube.com/watch?v=so6ExplQlaY", // Kiss The Rain - Yiruma
            "https://www.youtube.com/watch?v=HpxCc8qqPpA", // Opus 23 - Dustin O'Halloran
            "https://www.youtube.com/watch?v=9E6b3swbnWg", // Nocturne Op.9 No.2 - Chopin
            "https://www.youtube.com/watch?v=NvryolGa19A", // Comptine d’un autre été - Yann Tiersen
            "https://www.youtube.com/watch?v=EvxS_bJ0yOU", // Una Mattina - Ludovico Einaudi
            "https://www.youtube.com/watch?v=y8AWFf7EAc4", // Hallelujah - Jeff Buckley
            "https://www.youtube.com/watch?v=RBumgq5yVrA", // Let Her Go - Passenger
            "https://www.youtube.com/watch?v=6VyiB9txraE", // Heal - Tom Odell
            "https://www.youtube.com/watch?v=ghPcYqn0p4Y", // Breathe Me - Sia
            "https://www.youtube.com/watch?v=nyuo9-OjNNg"  // The Moon Song - Karen O
    );

    private final static List<String> lonelyMusic = List.of(
            "https://www.youtube.com/watch?v=UfcAVejslrU", // Weightless - Marconi Union
            "https://www.youtube.com/watch?v=CvFH_6DNRCY", // Clair de Lune - Claude Debussy
            "https://www.youtube.com/watch?v=S-Xm7s9eGxU", // Gymnopédie No.1 - Erik Satie
            "https://www.youtube.com/watch?v=7maJOI3QMu0", // River Flows in You - Yiruma
            //  // Experience - Ludovico Einaudi
            "https://www.youtube.com/watch?v=kcihcYEOeic", // Nuvole Bianche - Ludovico Einaudi
            "https://www.youtube.com/watch?v=rtOvBOTyX00", // A Thousand Years - Christina Perri (Piano)
            "https://www.youtube.com/watch?v=NlprozGcs80", // Canon in D - Pachelbel
            "https://www.youtube.com/watch?v=TJ6Mzvh3XCc", // Spiegel im Spiegel - Arvo Pärt
            "https://www.youtube.com/watch?v=4zLfCnGVeL4", // The Sound of Silence - Simon & Garfunkel
            "https://www.youtube.com/watch?v=so6ExplQlaY", // Kiss The Rain - Yiruma
            "https://www.youtube.com/watch?v=HpxCc8qqPpA", // Opus 23 - Dustin O'Halloran
            "https://www.youtube.com/watch?v=9E6b3swbnWg", // Nocturne Op.9 No.2 - Chopin
            "https://www.youtube.com/watch?v=NvryolGa19A", // Comptine d’un autre été - Yann Tiersen
            "https://www.youtube.com/watch?v=EvxS_bJ0yOU", // Una Mattina - Ludovico Einaudi
            "https://www.youtube.com/watch?v=y8AWFf7EAc4", // Hallelujah - Jeff Buckley
            "https://www.youtube.com/watch?v=RBumgq5yVrA", // Let Her Go - Passenger
            "https://www.youtube.com/watch?v=6VyiB9txraE", // Heal - Tom Odell
            "https://www.youtube.com/watch?v=ghPcYqn0p4Y", // Breathe Me - Sia
            "https://www.youtube.com/watch?v=nyuo9-OjNNg"  // The Moon Song - Karen O
    );

    private final static List<String> sadMusic = List.of(
            "https://www.youtube.com/watch?v=UfcAVejslrU", // Weightless - Marconi Union
            "https://www.youtube.com/watch?v=CvFH_6DNRCY", // Clair de Lune - Claude Debussy
            "https://www.youtube.com/watch?v=S-Xm7s9eGxU", // Gymnopédie No.1 - Erik Satie
            "https://www.youtube.com/watch?v=7maJOI3QMu0", // River Flows in You - Yiruma
             // Experience - Ludovico Einaudi
            "https://www.youtube.com/watch?v=kcihcYEOeic", // Nuvole Bianche - Ludovico Einaudi
            "https://www.youtube.com/watch?v=rtOvBOTyX00", // A Thousand Years - Christina Perri (Piano)
            "https://www.youtube.com/watch?v=NlprozGcs80", // Canon in D - Pachelbel
            "https://www.youtube.com/watch?v=TJ6Mzvh3XCc", // Spiegel im Spiegel - Arvo Pärt
            "https://www.youtube.com/watch?v=4zLfCnGVeL4", // The Sound of Silence - Simon & Garfunkel
            "https://www.youtube.com/watch?v=so6ExplQlaY", // Kiss The Rain - Yiruma
            "https://www.youtube.com/watch?v=HpxCc8qqPpA", // Opus 23 - Dustin O'Halloran
            "https://www.youtube.com/watch?v=9E6b3swbnWg", // Nocturne Op.9 No.2 - Chopin
            "https://www.youtube.com/watch?v=NvryolGa19A", // Comptine d’un autre été - Yann Tiersen
            "https://www.youtube.com/watch?v=EvxS_bJ0yOU", // Una Mattina - Ludovico Einaudi
            "https://www.youtube.com/watch?v=y8AWFf7EAc4", // Hallelujah - Jeff Buckley
            "https://www.youtube.com/watch?v=RBumgq5yVrA", // Let Her Go - Passenger
            "https://www.youtube.com/watch?v=6VyiB9txraE", // Heal - Tom Odell
            "https://www.youtube.com/watch?v=ghPcYqn0p4Y", // Breathe Me - Sia
            "https://www.youtube.com/watch?v=nyuo9-OjNNg"  // The Moon Song - Karen O
    );

    private final static List<String> angryMusic = List.of(
            "https://www.youtube.com/watch?v=UfcAVejslrU", // Weightless - Marconi Union
            "https://www.youtube.com/watch?v=CvFH_6DNRCY", // Clair de Lune - Claude Debussy
            "https://www.youtube.com/watch?v=S-Xm7s9eGxU", // Gymnopédie No.1 - Erik Satie
            "https://www.youtube.com/watch?v=7maJOI3QMu0", // River Flows in You - Yiruma
            "https://www.youtube.com/watch?v=1JHj1Ad30Xk", // Experience - Ludovico Einaudi
            "https://www.youtube.com/watch?v=kcihcYEOeic", // Nuvole Bianche - Ludovico Einaudi
            "https://www.youtube.com/watch?v=rtOvBOTyX00", // A Thousand Years - Christina Perri (Piano)
            "https://www.youtube.com/watch?v=NlprozGcs80", // Canon in D - Pachelbel
            "https://www.youtube.com/watch?v=TJ6Mzvh3XCc", // Spiegel im Spiegel - Arvo Pärt
            "https://www.youtube.com/watch?v=4zLfCnGVeL4", // The Sound of Silence - Simon & Garfunkel
            "https://www.youtube.com/watch?v=so6ExplQlaY", // Kiss The Rain - Yiruma
            "https://www.youtube.com/watch?v=HpxCc8qqPpA", // Opus 23 - Dustin O'Halloran
            "https://www.youtube.com/watch?v=9E6b3swbnWg", // Nocturne Op.9 No.2 - Chopin
            "https://www.youtube.com/watch?v=NvryolGa19A", // Comptine d’un autre été - Yann Tiersen
            "https://www.youtube.com/watch?v=EvxS_bJ0yOU", // Una Mattina - Ludovico Einaudi
            "https://www.youtube.com/watch?v=y8AWFf7EAc4", // Hallelujah - Jeff Buckley
            "https://www.youtube.com/watch?v=RBumgq5yVrA", // Let Her Go - Passenger
            "https://www.youtube.com/watch?v=6VyiB9txraE", // Heal - Tom Odell
            "https://www.youtube.com/watch?v=ghPcYqn0p4Y", // Breathe Me - Sia
            "https://www.youtube.com/watch?v=nyuo9-OjNNg"  // The Moon Song - Karen Ov=2pVlwFj8F6s"  // Adagio in G Minor - Albinoni
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
