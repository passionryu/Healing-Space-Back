package website.server.Domain.Healing_Space_News.Healing_Blog.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 값 자동 생성
    private Long blogId;

    private String title;
    private String author;
    private String profileImg;
    private LocalDate date;
    private String link;
    private String thumbnail;


}
