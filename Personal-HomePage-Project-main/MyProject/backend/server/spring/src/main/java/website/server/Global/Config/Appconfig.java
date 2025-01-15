package website.server.Global.Config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration // 이 클래스는 스프링 빈을 위한 설정을 제공
@MapperScan("website.server.Domain.Member.Mapper") // MyBatis 매퍼 인터페이스가 위치한 패키지 스캔
@MapperScan("website.server.Domain.HealingProgram.HealingService.DewRecord.Mapper")
@MapperScan("website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.Mapper")
@MapperScan("website.server.Domain.Healing_Space_News.Our_News.Mapper")
public class Appconfig {
}
