package website.server.Domain.Healing_Space_News.Healing_Blog.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import website.server.Domain.Healing_Space_News.Healing_Blog.DTO.Response.BlogResponse;
import website.server.Domain.Healing_Space_News.Healing_Blog.DTO.Response.BlogResponseWithId;
import website.server.Domain.Healing_Space_News.Healing_Blog.Service.BlogService;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/blog")
@Tag(name = "Healing Blog", description = "Healing Blog API")
public class BlogController {

    private final BlogService blogService;

    /* 자정마다 자동으로 실행되는 API */
    @Scheduled(cron = "0 0 0 * * *")  // ⏰ 매일 자정(00:00:00) 실행
    public ResponseEntity<List<BlogResponse>>  crawlHealingBlogs() throws IOException {

        String query = "힐링+블로그";
        int limit = 9;

        blogService.deleteBlogDB();
        List<BlogResponse> blogs = blogService.crawlAndSaveBlogs(query, limit);
        return ResponseEntity.ok(blogs);
    }

    /**
     * 힐링 블로그 크롤링 후 DB 저장 API
     */
    @Operation(summary = "힐링 블로그 크롤링 후 DB 저장 API", description = "네이버에서 '힐링 블로그' 관련 게시글을 6개 크롤링하여 DB에 저장")
    @PostMapping("/crawl")
    public ResponseEntity<List<BlogResponse>> crawlingTest() throws IOException {

        String query = "힐링+블로그";
        int limit = 9;

        blogService.deleteBlogDB();
        List<BlogResponse> blogs = blogService.crawlAndSaveBlogs(query, limit);
        return ResponseEntity.ok(blogs);

    }

    /**
     * DB에서 크롤링한 힐링 블로그 정보 조회 API
     * @return
     * @throws IOException
     */
    @Operation(summary = " DB에서 크롤링한 힐링 블로그 정보 조회 API ", description = "")
    @GetMapping("")
    public ResponseEntity<List<BlogResponseWithId>> getBlogs() throws IOException {

        /* 블로그 데이터 반환 */
        List<BlogResponseWithId> blogs = blogService.getBlogs();

        return ResponseEntity.ok(blogs);
    }

}
