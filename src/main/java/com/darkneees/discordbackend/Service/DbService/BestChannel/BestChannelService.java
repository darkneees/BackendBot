package com.darkneees.discordbackend.Service.DbService.BestChannel;

import com.darkneees.discordbackend.Entity.BestChannelEntity;
import com.darkneees.discordbackend.Exception.NoEntityException;
import net.dv8tion.jda.api.entities.Message;

import java.time.LocalDate;
import java.util.Optional;

public interface BestChannelService {

    Optional<BestChannelEntity> getBestChannelByIdAndGuildIdAndTime(long UserId, long GuildId, LocalDate date);
    Long getBestChannelByGuildIdAndMaxCount(long guildId) throws NoEntityException;
    void ChangeCountMessages(Message message);
}
