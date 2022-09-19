package com.darkneees.discordbackend;

import com.darkneees.discordbackend.listener.GuildMessageSendListener;
import com.darkneees.discordbackend.service.bot.BotServiceImpl;
import com.darkneees.discordbackend.service.dbservice.bestchannel.BestChannelServiceImpl;
import com.darkneees.discordbackend.service.dbservice.bestmember.BestMemberServiceImpl;
import com.darkneees.discordbackend.service.dbservice.guild.GuildServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiscordBackendApplication implements CommandLineRunner {

    private final BotServiceImpl botService;
    private final BestMemberServiceImpl bestMemberService;
    private final BestChannelServiceImpl bestChannelService;
    private final GuildServiceImpl guildService;

    public DiscordBackendApplication(BotServiceImpl botService,
                                     BestMemberServiceImpl bestMemberService,
                                     BestChannelServiceImpl bestChannelService,
                                     GuildServiceImpl guildService) {
        this.botService = botService;
        this.bestMemberService = bestMemberService;
        this.bestChannelService = bestChannelService;
        this.guildService = guildService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DiscordBackendApplication.class, args);
    }

    @Override
    public void run(String... args) {
        this.botService.startBot();
        this.botService.registerListeners(new GuildMessageSendListener(bestMemberService, bestChannelService, guildService));
    }
}
