package website.server.Domain.Healing_Space_News.Healing_Blog.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import website.server.Domain.Healing_Space_News.Healing_Blog.DTO.Response.BlogResponse;
import website.server.Domain.Healing_Space_News.Healing_Blog.Mapper.BlogMapper;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService{

    private final BlogMapper blogMapper;

    /**
     * 크롤링 후 DB에 저장 메서드
     * @param query 검색 키워드
     * @param limit 가져올 개수
     * @return
     */
    @Override
    public List<BlogResponse> crawlAndSaveBlogs(String query, int limit) throws IOException {

        /* 작성자 프로필 이미지는 필요 없을 듯, 썸네일 까지만 해결할 것 */

        /**
         * [추출할 데이터]
         * 1. title
         * 2. author
         * 3. profile_img
         * 4. date
         * 5. link
         * 6. thumbNail
         */

        String url = "https://search.naver.com/search.naver?query="+query;
        Document doc = Jsoup.connect(url).get();

        // 1. title
        Element title = doc.selectFirst("a.title_link");
        assert title != null; log.info("title : {} ",title.text());

        // 2. author
        Element author = doc.selectFirst("a.name");
        assert author != null; log.info("author : {} ",author.text());

        // 3. profile_img -> 셀리니움으로 해야 할 것 같음
        Element profile_img = doc.selectFirst("img.img");
        if (profile_img != null) {
            String imageUrl = profile_img.attr("data-src");  // 실제 이미지 URL
            if (imageUrl.isEmpty()) {
                imageUrl = profile_img.attr("data-original"); // 다른 속성 확인
            }
            if (imageUrl.isEmpty()) {
                imageUrl = profile_img.attr("src"); // 기본 src 확인
            }
            log.info("image url : {} ",imageUrl);
        }

        // 4. date
        Element date = doc.selectFirst("div.user_info span.sub");
        assert date != null; log.info("date : {} ",date.text());

        // 5. link
        Element linkElement = doc.selectFirst("a.dsc_link");
        if (linkElement != null) {
            String blogUrl = linkElement.attr("href");
            log.info("link: {} " ,blogUrl);
        } else {
            log.info("link == null ");
        }
        //assert link != null; log.info("link : {} ",link.text());

        // 6. thumbNail
        //Element thumbNail = doc.selectFirst("a.name");
        Element thumbNail = doc.selectFirst("a.thumb_link img");
        if (thumbNail != null) {
            String imgSrc = thumbNail.attr("src"); // 이미지 URL
            log.info("imgSrc : {} ",imgSrc);
        } else {
            log.info("이미지 찾을 수 없음");
        }

        return null;
    }
}
