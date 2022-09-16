package com.darkneees.discordbackend.Service.RestService;

import com.darkneees.discordbackend.Exception.NoEntityException;
import com.darkneees.discordbackend.Service.Bot.BotServiceImpl;
import com.darkneees.discordbackend.Service.DbService.BestChannel.BestChannelServiceImpl;
import com.darkneees.discordbackend.Service.DbService.BestMember.BestMemberServiceImpl;
import com.darkneees.discordbackend.Service.DbService.Guild.GuildServiceImpl;
import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class RestService {

    private final BotServiceImpl botService;
    private final BestMemberServiceImpl bestMemberService;
    private final BestChannelServiceImpl bestChannelService;
    private final GuildServiceImpl guildService;

    public RestService(BotServiceImpl botService, BestMemberServiceImpl bestMemberService, BestChannelServiceImpl bestChannelService, GuildServiceImpl guildService) {
        this.botService = botService;
        this.bestMemberService = bestMemberService;
        this.bestChannelService = bestChannelService;
        this.guildService = guildService;
    }

    @Async
    public CompletableFuture<List<HashMap<String, String>>> getAllGuilds() {

        return CompletableFuture.supplyAsync(() -> {
            List<HashMap<String, String>> guildsInfoMap = new ArrayList<>();
            botService.getJDA().getGuilds().forEach((element -> guildsInfoMap.add(new HashMap<>(){{
                put("id", element.getId());
                put("name", element.getName());
            }
            })));
            return guildsInfoMap;
        });
    }

    @Async
    public CompletableFuture<HashMap<String, String>> getBestMember(long id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                HashMap<String, String> bestMemberInfo = new HashMap<>();

                long userId = bestMemberService.getBestMemberByGuildIdAndMaxCount(id);
                User user = botService.getJDA().retrieveUserById(userId).complete();
                bestMemberInfo.put("id", user.getId());
                bestMemberInfo.put("name", user.getName());
                bestMemberInfo.put("avatar", user.getAvatarUrl());

                return bestMemberInfo;

            } catch (NoEntityException e) {

                throw new RuntimeException(e);
            }
        });
    }

    @Async
    public CompletableFuture<HashMap<String, String>> getBestChannel(long id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                long channelId = bestChannelService.getBestChannelByGuildIdAndMaxCount(id);

                HashMap<String, String> bestChannelInfo = new HashMap<>();
                Channel channel = botService.getJDA().getTextChannelById(channelId);
                bestChannelInfo.put("id", channel.getId());
                bestChannelInfo.put("name", channel.getName());

                return bestChannelInfo;
            } catch (NoEntityException e) {
                throw new RuntimeException(e);
            }

        });
    }

    @Async
    public CompletableFuture<HashMap<String, String>> getMessagesInHour(long id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                long count = guildService.getCountByGuildId(id);
                HashMap<String, String> messageInHour = new HashMap<>();

                Guild guild = botService.getJDA().getGuildById(id);

                messageInHour.put("count", String.valueOf(count));
                messageInHour.put("id", guild.getId());
                messageInHour.put("name", guild.getName());

            return messageInHour;

            } catch (NoEntityException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
