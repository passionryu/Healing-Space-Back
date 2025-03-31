package website.server.Global.Config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("website.server.Domain.Member.Mapper")
@MapperScan("website.server.Domain.HealingProgram.HealingService.DewRecord.Mapper")
@MapperScan("website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.Mapper")
@MapperScan("website.server.Domain.Healing_Space_News.Our_News.Mapper")
@MapperScan("website.server.Domain.HealingProgram.HealingStore.Mapper")
@MapperScan("website.server.Domain.HealingProgram.AiService.AiRecommend.Mapper")
@MapperScan("website.server.Domain.HealingProgram.AiService.AiSnapShot.Mapper")
@MapperScan("website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Mapper")
@MapperScan("website.server.Domain.Healing_Space_News.Healing_Blog.Mapper")
@MapperScan("website.server.Domain.HealingProgram.AiService.AiChatBot.Mapper")
public class Appconfig {
}
