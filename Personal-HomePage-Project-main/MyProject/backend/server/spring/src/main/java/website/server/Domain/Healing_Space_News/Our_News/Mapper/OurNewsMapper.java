package website.server.Domain.Healing_Space_News.Our_News.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import website.server.Domain.Healing_Space_News.Our_News.DTO.Response.GetNewsResponse;
import website.server.Domain.Healing_Space_News.Our_News.DTO.Response.NewsListResponse;
import website.server.Domain.Healing_Space_News.Our_News.DTO.Response.OurNewsCommentResponse;

import java.util.List;

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

    /**
     * 게시글 삭제
     * @param ourNewsNumber 삭제하고자 하는 게시글 고유번호
     */
    void deleteNews(Long ourNewsNumber);

    /**
     * 게시글 리스트 조회
     * @return 게시글 썸네일 리스트
     */
    List<NewsListResponse> getNewsList();

    /**
     * 게시글 조회
     * @param ourNewsNumber 조회하고자 하는 게시글 고유번호
     * @return 게시글 정보
     */
    GetNewsResponse getNews(Long ourNewsNumber);

    /**
     * 댓글 달기
     * @param userNumber 사용자 고유 번호
     * @param ourNewsNumber 댓글을 달고자 하는 게시글 고유번호
     * @param content 댓글 본문
     */
    void postComment(@Param("userNumber")Long userNumber,
                     @Param("ourNewsNumber") Long ourNewsNumber,
                     @Param("content") String content);

    /**
     * 댓글 조회
     * @param ourNewsNumber 댓글을 조회하고자 하는 게시글의 고유번호
     * @return 댓글 리스트 반환
     */
    List<OurNewsCommentResponse> getComment(Long ourNewsNumber);

    /**
     * 댓글 삭제
     * @param userNumber 사용자 고유 번호
     * @param commentId 삭제하고자 하는 댓글의 고유번호
     */
    void deleteComment(@Param("userNumber") Long userNumber,
                       @Param("commentId")Long commentId);

}
