package website.server.Domain.HealingProgram.HealingService.DewCalendar.DTO.Request;

import website.server.Domain.HealingProgram.HealingService.DewCalendar.Entity.Diary;

import java.time.LocalDate;

public record FullDiaryRequest(

         Long diaryNumber,
         Long userNumber,
         String title,
         String diary,
         String emotion,
         String weather,
         LocalDate date,
         String healingMessage,
         String healingMusic
) {
    // FullDiaryRequest를 Diary 엔티티로 변환하는 메서드
    public Diary toEntity() {
        return Diary.builder()
                .diaryNumber(this.diaryNumber)
                .userNumber(this.userNumber)
                .title(this.title)
                .diary(this.diary)
                .emotion(this.emotion)
                .weather(this.weather)
                .date(this.date)
                .healingMessage(this.healingMessage)
                .healingMusic(this.healingMusic)
                .build();
    }
}
