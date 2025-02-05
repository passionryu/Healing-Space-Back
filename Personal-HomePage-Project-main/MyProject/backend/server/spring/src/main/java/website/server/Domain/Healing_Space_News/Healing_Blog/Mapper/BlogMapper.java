package website.server.Domain.Healing_Space_News.Healing_Blog.Mapper;

import org.apache.ibatis.annotations.Mapper;
import website.server.Domain.Healing_Space_News.Healing_Blog.Entity.Blog;

import java.util.List;

@Mapper
public interface BlogMapper {

    // 블로그 데이터를 DB에 저장하는 메소드
    void insertBlog(Blog blog);

    // 블로그 데이터를 조회하는 메소드 (예시)
    //List<Blog> selectAllBlogs();

}
