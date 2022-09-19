package com.darkneees.discordbackend.service.dbservice.bestchannel;

import com.darkneees.discordbackend.entity.BestChannelEntity;
import com.darkneees.discordbackend.exception.NoEntityException;
import net.dv8tion.jda.api.entities.Message;

import java.time.LocalDate;
import java.util.Optional;

public interface BestChannelService {

    Optional<BestChannelEntity> getBestChannel(long UserId, long GuildId, LocalDate date);
    Long getBestChannelByGuildIdAndMaxCount(long guildId) throws NoEntityException;
    void updateBestChannelEntity(Message message);
}
