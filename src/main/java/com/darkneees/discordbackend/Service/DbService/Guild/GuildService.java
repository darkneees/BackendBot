package com.darkneees.discordbackend.Service.DbService.Guild;

import com.darkneees.discordbackend.Entity.GuildEntity;
import net.dv8tion.jda.api.entities.Message;

import java.util.Optional;

public interface GuildService {

    void addMessageInHour(Message message);
    Optional<GuildEntity> getGuildById(long id);

}
