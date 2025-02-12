package website.server.Domain.Healing_Space_News.Healing_Blog.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import website.server.Domain.Healing_Space_News.Healing_Blog.DTO.Response.BlogResponseWithId;

import java.util.List;

@Mapper
public interface BlogMapper {

    /**
     * 블로그 데이터를 모두 삭제하는 메서드
     */
    void deleteBlog();

    /**
     * 블로그 데이터를 DB에 저장하는 메서드
     * @param title
     * @param author
     * @param profileImg
     * @param url
     * @param thumbNail
     */
    void insertBlog(@Param("title") String title,
                    @Param("author") String author,
                    @Param("profileImg") String profileImg,
                    @Param("url") String url,
                    @Param("thumbNail") String thumbNail
                    );

    /**
     * 블로그의 모든 데이터를 조회하는 인터페이스
     * @return 모든 데이터 반환
     */
    List<BlogResponseWithId> selectAllBlogs();

}
