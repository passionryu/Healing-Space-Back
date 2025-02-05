package website.server.Domain.Healing_Space_News.Healing_Blog.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import website.server.Domain.Healing_Space_News.Healing_Blog.DTO.Response.BlogResponse;
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

    /**
     * 힐링 블로그 크롤링 후 DB에 저장 API
     * @param query 검색 키워드
     * @param limit 가져올 개수
     * @return 크롤링 결과
     */
    @Operation(summary = "힐링 블로그 크롤링 후 DB 저장 API", description = "네이버에서 '힐링 블로그' 관련 게시글을 6개 크롤링하여 DB에 저장")
    @PostMapping("/crawl")
    public ResponseEntity<List<BlogResponse>> crawlHealingBlogs(
            @RequestParam(value = "query" ,defaultValue = "힐링+블로그") String query, // 검색 키워드 (기본값: '힐링 블로그')
            @RequestParam(value = "limit" ,defaultValue = "6") int limit // 가져올 개수 (기본값: 6개)
    ) throws IOException {
        List<BlogResponse> blogs = blogService.crawlAndSaveBlogs(query, limit);
        return ResponseEntity.ok(blogs);
    }

    /* DB에서 크롤링한 힐링 블로그 정보 조회 API */

    /* 블로그 클릭 시 링크로 이동 API */
}
