package com.darkneees.discordbackend.service.DbService.Guild;

import com.darkneees.discordbackend.entity.GuildEntity;
import com.darkneees.discordbackend.exception.NoEntityException;
import net.dv8tion.jda.api.entities.Message;

import java.util.Optional;

public interface GuildService {

    void updateGuildEntity(Message message);
    Optional<GuildEntity> getGuildById(long id);
    Long getCountByGuildId(long id) throws NoEntityException;

}
