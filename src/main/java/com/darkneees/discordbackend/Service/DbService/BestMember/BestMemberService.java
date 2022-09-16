package com.darkneees.discordbackend.Service.DbService.BestMember;

import com.darkneees.discordbackend.Entity.BestMemberEntity;
import com.darkneees.discordbackend.Exception.NoEntityException;
import net.dv8tion.jda.api.entities.Message;

import java.time.LocalDate;
import java.util.Optional;

public interface BestMemberService {

    Optional<BestMemberEntity> getBestMemberByIdAndGuildIdAndTime(long UserId, long GuildId, LocalDate date);
    Long getBestMemberByGuildIdAndMaxCount(long guildId) throws NoEntityException;
    void ChangeCountMessages(Message message);
}
