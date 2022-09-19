package com.darkneees.discordbackend.listener;

import com.darkneees.discordbackend.service.dbservice.bestchannel.BestChannelServiceImpl;
import com.darkneees.discordbackend.service.dbservice.bestmember.BestMemberServiceImpl;
import com.darkneees.discordbackend.service.dbservice.guild.GuildServiceImpl;
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
        memberService.updateBestMemberEntity(event.getMessage());
        channelService.updateBestChannelEntity(event.getMessage());
        guildService.updateGuildEntity(event.getMessage());
    }
}
