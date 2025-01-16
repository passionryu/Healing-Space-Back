package website.server.Domain.Healing_Space_News.Our_News.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import website.server.Domain.Healing_Space_News.Our_News.DTO.Request.PostCommentRequest;
import website.server.Domain.Healing_Space_News.Our_News.DTO.Request.PostNewsRequest;
import website.server.Domain.Healing_Space_News.Our_News.DTO.Response.GetNewsResponse;
import website.server.Domain.Healing_Space_News.Our_News.DTO.Response.NewsListResponse;
import website.server.Domain.Healing_Space_News.Our_News.Mapper.OurNewsMapper;
import website.server.Domain.Healing_Space_News.Our_News.Util.OurNews_Set_Image_Path;
import website.server.Global.JWT.JwtService;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OurNewsServiceImpl implements OurNewsService{

    private final OurNewsMapper ourNewsMapper;
    private final JwtService jwtService;
    private final OurNews_Set_Image_Path ourNewsSetImagePath;

    /**
     * 게시글 올리기 API
     * @param request 사용자 요청
     * @param postNewsRequest 게시글 데이터
     */
    @Override
    public void postNews(HttpServletRequest request,
                         PostNewsRequest postNewsRequest) {

        /* 파일 업로드 */
        MultipartFile file = postNewsRequest.file();
        String imgPath = null;

        /* 이미지 파일이 있을 경우 파일을 업로드하고 경로를 저장 */
        if (file != null && !file.isEmpty()) {
            try {
                imgPath = ourNewsSetImagePath.uploadPhoto(file); // 파일 경로를 반환받음
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /* 데이터 추출 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);
        String title = postNewsRequest.title();
        String content = postNewsRequest.content();

        /* 데이터 저장 */
        ourNewsMapper.postNews(userNumber,title,content,imgPath);

    }

    /**
     * 게시글 삭제 API
     * @param ourNewsNumber 삭제하고자 하는 게시글의 고유번호
     */
    @Override
    public void deleteNews(Long ourNewsNumber) {

        /* 게시글 삭제 */
        ourNewsMapper.deleteNews(ourNewsNumber);
    }

    /**
     * 게시글 리스트 조회 메서드
     * @return 게시글 썸네일 리스트
     */
    @Override
    public List<NewsListResponse> getNewsList() {

        /* 게시글 리스트 조회  */
        return ourNewsMapper.getNewsList();
    }

    /**
     * 게시글 조회 메서드
     * @param ourNewsNumber 조회하고자 하는 게시글의 고유 번호
     * @return 게시글 정보
     */
    @Override
    public GetNewsResponse getNews(Long ourNewsNumber) {

        /* 게시글 조회 */
        return ourNewsMapper.getNews(ourNewsNumber);
    }

    /**
     * 댓글 달기 API
     * @param request 사용자 요청
     * @param postCommentRequest 댓글 업로드 요청 데이터
     */
    @Override
    public void postComment(HttpServletRequest request, PostCommentRequest postCommentRequest) {

        /*  our_news_comment DB테이블에 저장할 정보 추출 */
         Long userNumber = jwtService.extractUserNumberFromRequest(request);
         Long ourNewsNumber = postCommentRequest.ourNewsNumber();
         String content = postCommentRequest.content();

         /* 댓글 저장 */
         ourNewsMapper.postComment(userNumber,ourNewsNumber,content);

    }
}
