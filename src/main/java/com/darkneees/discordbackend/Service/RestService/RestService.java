package com.darkneees.discordbackend.Service.RestService;

import com.darkneees.discordbackend.Entity.BestMemberEntity;
import com.darkneees.discordbackend.Service.Bot.BotServiceImpl;
import com.darkneees.discordbackend.Service.DbService.BestChannel.BestChannelServiceImpl;
import com.darkneees.discordbackend.Service.DbService.BestMember.BestMemberServiceImpl;
import com.darkneees.discordbackend.Service.DbService.Guild.GuildServiceImpl;
import net.dv8tion.jda.api.entities.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
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

            HashMap<String, String> bestMemberInfo = new HashMap<>();
            long userId = bestMemberService.getBestMemberByGuildIdAndMaxCount(id); // Optional
            if(userId != 0) {
                User user = botService.getJDA().retrieveUserById(userId).complete();
                bestMemberInfo.put("id", user.getId());
                bestMemberInfo.put("name", user.getName());
                bestMemberInfo.put("avatar", user.getAvatarUrl());
            }
            return bestMemberInfo;
        });
    }
}
