package website.server.Domain.Member.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import website.server.Domain.Member.Entity.Member;

@Mapper
public interface MemberMapper {

    //@Insert("INSERT INTO member (username, email, password, birth) " +
    //      "VALUES (#{username}, #{email}, #{password}, #{birth})")
    void register(Member member);
}
