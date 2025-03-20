package website.server.Domain.Healing_Space_News.Healing_Blog.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import website.server.Domain.Healing_Space_News.Healing_Blog.DTO.Response.BlogResponse;
import website.server.Domain.Healing_Space_News.Healing_Blog.DTO.Response.BlogResponseWithId;
import website.server.Domain.Healing_Space_News.Healing_Blog.Mapper.BlogMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService{

    private final BlogMapper blogMapper;

    /**
     * DB 삭제 메서드
     */
    @Override
    public void deleteBlogDB() {
        blogMapper.deleteBlog();
    }

    /**
     * 크롤링 후 DB에 저장 메서드
     * @param query 검색 키워드
     * @param limit 가져올 개수
     * @return
     */
    @Override
    public List<BlogResponse> crawlAndSaveBlogs(String query, int limit) throws IOException {

        String url = "https://search.naver.com/search.naver?query=" + query;
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
                .referrer("https://www.naver.com") // 리퍼러 추가
                .get();

        Elements authors = doc.select("a.name");
        for (int i = 0; i < Math.min(limit, authors.size()); i++) {
            authors.get(i).text();
        }
        List<BlogResponse> blogResponses = new ArrayList<>(); // 블로그 응답 리스트 초기화

        Elements linkElems = doc.select("a.dsc_link"); // 모든 블로그 링크를 가져옴
        for (int i = 0; i < Math.min(limit, linkElems.size()); i++) { // 최대 n개까지 반복
            String blogUrl = linkElems.get(i).attr("href"); // 각 링크의 href 속성 추출
            log.info("{}번 blog : {}", i + 1, blogUrl); // 각 링크 출력

            String title = extractTitleFromBlog(blogUrl);
            log.info("{}번 title : {}", i + 1, title);

            String author = authors.get(i).text();
            log.info("{}번 author : {}", i + 1, authors.get(i).text());

            String ProfileUrl = extractProfileFromBlog(blogUrl);
            log.info("{}번 Profile url: {}", i + 1, ProfileUrl);

            String thumbNailUrl = extractThumbnailFromBlog(blogUrl);
            log.info("{}번 thumbNail url: {}", i + 1, thumbNailUrl);

            /* BlogResponse 객체 생성 후 리스트에 추가 */
            BlogResponse blogResponse = new BlogResponse(
                    title,
                    author,
                    blogUrl,
                    ProfileUrl,
                    thumbNailUrl
            );
            /* 리스트에 추가 */
            blogResponses.add(blogResponse);

            /* DB 저장 */
            blogMapper.insertBlog(title,author,ProfileUrl,blogUrl,thumbNailUrl);
        }
        return blogResponses;
    }

    /**
     * 블로그 조회 메서드
     * @return 모든 블로그 반환
     */
    @Override
    public List<BlogResponseWithId> getBlogs() {
        return blogMapper.selectAllBlogs();
    }

    /**
     * 블로그에서 제목 추출 - 블로그 직접 입장 후 제목 추출
     * @param blogUrl 블로그 url
     * @return 블로그 제목 반환
     */
    private String extractTitleFromBlog(String blogUrl) {
        String blogTitle = "";
        try {
            // 1. 네이버 블로그 외부 페이지 접속
            Document blogDoc = Jsoup.connect(blogUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
                    .referrer("https://www.naver.com")
                    .get();

            // 2. iframe 요소 추출 (네이버 블로그는 실제 콘텐츠를 iframe으로 로드)
            Element iframeElem = blogDoc.selectFirst("iframe#mainFrame");
            if (iframeElem == null) {
                log.warn("iframe element not found in blog page: {}", blogUrl);
                return "";
            }
            String iframeSrc = iframeElem.attr("src");

            // 3. iframeSrc가 상대 URL인 경우 절대 URL로 변환
            if (!iframeSrc.startsWith("http")) {
                iframeSrc = "https://blog.naver.com" + iframeSrc;
            }

            // 4. iframe URL에 접속하여 실제 콘텐츠 페이지 가져오기
            Document iframeDoc = Jsoup.connect(iframeSrc)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
                    .referrer("https://www.naver.com")
                    .get();

            // 5. 블로그 제목 추출 (주어진 HTML에서 제목을 나타내는 <span> 태그 추출)
            Element titleElem = iframeDoc.selectFirst(".se-fs-");
            if (titleElem != null) {
                blogTitle = titleElem.text(); // 제목 텍스트 추출
            } else {
                log.warn("제목을 찾을 수 없습니다. blogUrl: {}", blogUrl);
            }
        } catch (IOException e) {
            log.error("블로그 페이지 접속 또는 제목 추출 중 오류 발생: {}", blogUrl, e);
        }
        return blogTitle;
    }

    /**
     * 블로그에서 썸네일 추출 - 블로그 직접 입장 후 첫번째 이미지 추출
     * @param blogUrl 블로그 url
     * @return 썸네일 이미지 반환
     */
    private String extractThumbnailFromBlog(String blogUrl) {
        String thumbnailUrl = "";
        try {
            // 1. 네이버 블로그 외부 페이지 접속
            Document blogDoc = Jsoup.connect(blogUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
                    .referrer("https://www.naver.com")
                    .get();
            //log.info("blogDoc: {}", blogDoc);

            // 2. iframe 요소 추출 (네이버 블로그는 실제 콘텐츠를 iframe으로 로드)
            Element iframeElem = blogDoc.selectFirst("iframe#mainFrame");
            if (iframeElem == null) {
                log.warn("iframe element not found in blog page: {}", blogUrl);
                return "";
            }
            String iframeSrc = iframeElem.attr("src");

            // 3. iframeSrc가 상대 URL인 경우 절대 URL로 변환
            if (!iframeSrc.startsWith("http")) {
                iframeSrc = "https://blog.naver.com" + iframeSrc;
            }

            // 4. iframe URL에 접속하여 실제 콘텐츠 페이지 가져오기
            Document iframeDoc = Jsoup.connect(iframeSrc)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
                    .referrer("https://www.naver.com")
                    .get();

            // 5. iframe 내부에서 첫번째 이미지 요소 추출 (네이버 블로그 페이지 구조에 따라 선택자 수정 필요)
            Element imgElem = iframeDoc.selectFirst("img.se-image-resource");


            if (imgElem != null) {
                thumbnailUrl = imgElem.attr("src");
            } else {
                log.warn("썸네일 이미지를 찾을 수 없습니다. blogUrl: {}", blogUrl);
            }
        } catch (IOException e) {
            log.error("블로그 페이지 접속 또는 이미지 추출 중 오류 발생: {}", blogUrl, e);
        }
        return thumbnailUrl;

    }

    /**
     * 블로그에서 프로필 이미지 추출 메서드
     * @param blogUrl 블로그 url
     * @return 프로필 이미지 반환
     */
    private String extractProfileFromBlog(String blogUrl) {
        String profileUrl = "";
        try {
            // 1. 네이버 블로그 외부 페이지 접속
            Document blogDoc = Jsoup.connect(blogUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
                    .referrer("https://www.naver.com")
                    .get();
            //log.info("blogDoc: {}", blogDoc);

            // 2. iframe 요소 추출 (네이버 블로그는 실제 콘텐츠를 iframe으로 로드)
            Element iframeElem = blogDoc.selectFirst("iframe#mainFrame");
            if (iframeElem == null) {
                log.warn("iframe element not found in blog page: {}", blogUrl);
                return "";
            }
            String iframeSrc = iframeElem.attr("src");

            // 3. iframeSrc가 상대 URL인 경우 절대 URL로 변환
            if (!iframeSrc.startsWith("http")) {
                iframeSrc = "https://blog.naver.com" + iframeSrc;
            }

            // 4. iframe URL에 접속하여 실제 콘텐츠 페이지 가져오기
            Document iframeDoc = Jsoup.connect(iframeSrc)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
                    .referrer("https://www.naver.com")
                    .get();

            Element profileImgElem = iframeDoc.selectFirst("a.link img");

            if (profileImgElem != null) {
                profileUrl = profileImgElem.attr("src");
            } else {
                log.warn("썸네일 이미지를 찾을 수 없습니다. blogUrl: {}", blogUrl);
            }
        } catch (IOException e) {
            log.error("블로그 페이지 접속 또는 이미지 추출 중 오류 발생: {}", blogUrl, e);
        }
        return profileUrl;

    }



}
