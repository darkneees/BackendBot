package com.darkneees.discordbackend.Listener;

import com.darkneees.discordbackend.Service.DbService.BestChannel.BestChannelServiceImpl;
import com.darkneees.discordbackend.Service.DbService.BestMember.BestMemberServiceImpl;
import com.darkneees.discordbackend.Service.DbService.Guild.GuildServiceImpl;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.SubscribeEvent;
import org.springframework.stereotype.Component;

@Component
public class GuildMessageSendListener {

    private final BestMemberServiceImpl memberService;
    private final BestChannelServiceImpl channelService;
    private final GuildServiceImpl guildService;

    public GuildMessageSendListener(BestMemberServiceImpl memberService,
                                    BestChannelServiceImpl channelService,
                                    GuildServiceImpl guildService) {
        this.memberService = memberService;
        this.channelService = channelService;
        this.guildService = guildService;
    }

    @SubscribeEvent
    public void execute (MessageReceivedEvent event) {
        memberService.ChangeCountMessages(event.getMessage());
        channelService.ChangeCountMessages(event.getMessage());
        guildService.addMessageInHour(event.getMessage());

    }
}
