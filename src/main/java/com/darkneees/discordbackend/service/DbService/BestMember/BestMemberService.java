package com.darkneees.discordbackend.service.DbService.BestMember;

import com.darkneees.discordbackend.entity.BestMemberEntity;
import com.darkneees.discordbackend.exception.NoEntityException;
import net.dv8tion.jda.api.entities.Message;

import java.time.LocalDate;
import java.util.Optional;

public interface BestMemberService {

    Optional<BestMemberEntity> getBestMember(long UserId, long GuildId, LocalDate date);
    Long getBestMemberByGuildIdAndMaxCount(long guildId) throws NoEntityException;
    void updateBestMemberEntity(Message message);
}
