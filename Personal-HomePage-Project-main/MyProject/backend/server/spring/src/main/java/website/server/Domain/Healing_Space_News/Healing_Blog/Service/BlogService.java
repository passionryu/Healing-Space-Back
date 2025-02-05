package website.server.Domain.Healing_Space_News.Healing_Blog.Service;

import website.server.Domain.Healing_Space_News.Healing_Blog.DTO.Response.BlogResponse;

import java.io.IOException;
import java.util.List;

public interface BlogService {

    /**
     * 크롤링 후 DB에 저장 메서드
     * @param query 검색 키워드
     * @param limit 가져올 개수
     * @return 크롤링 결과
     */
    List<BlogResponse>  crawlAndSaveBlogs(String query, int limit) throws IOException;


}
