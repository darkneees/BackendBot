package com.darkneees.discordbackend;

import com.darkneees.discordbackend.Listener.GuildMessageSendListener;
import com.darkneees.discordbackend.Service.Bot.BotService;
import com.darkneees.discordbackend.Service.DbService.BestChannel.BestChannelServiceImpl;
import com.darkneees.discordbackend.Service.DbService.BestMember.BestMemberServiceImpl;
import com.darkneees.discordbackend.Service.DbService.Guild.GuildServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiscordBackendApplication implements CommandLineRunner {

    private final BotService botService;
    private final BestMemberServiceImpl bestMemberService;
    private final BestChannelServiceImpl bestChannelService;
    private final GuildServiceImpl guildService;

    public DiscordBackendApplication(BotService botService,
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
