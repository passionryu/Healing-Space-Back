package website.server.Domain.Healing_Space_News.Our_News.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OurNewsMapper {

    /**
     * 게시글 업로드
     * @param userNumber 사용자 고유번호
     * @param title 게시글 제목
     * @param content 게시글 본문
     * @param img_path 사진의 경로
     */
    void postNews(@Param("userNumber") Long userNumber,
                  @Param("title") String title,
                  @Param("content") String content,
                  @Param("img_path") String img_path);


}
