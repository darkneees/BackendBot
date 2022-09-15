package com.darkneees.discordbackend.Service.DbService.BestChannel;

import com.darkneees.discordbackend.Entity.BestChannelEntity;
import net.dv8tion.jda.api.entities.Message;

import java.time.LocalDate;
import java.util.Optional;

public interface BestChannelService {

    Optional<BestChannelEntity> getBestChannelByIdAndGuildIdAndTime(long UserId, long GuildId, LocalDate date);
    void ChangeCountMessages(Message message);
}
