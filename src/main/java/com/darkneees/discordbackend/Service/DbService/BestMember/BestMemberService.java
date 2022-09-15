package com.darkneees.discordbackend.Service.DbService.BestMember;

import com.darkneees.discordbackend.Entity.BestMemberEntity;
import net.dv8tion.jda.api.entities.Message;

import java.time.LocalDate;
import java.util.Optional;

public interface BestMemberService {

    Optional<BestMemberEntity> getBestMemberByIdAndGuildIdAndTime(long UserId, long GuildId, LocalDate date);
    long getBestMemberByGuildIdAndMaxCount(long guildId);
    void ChangeCountMessages(Message message);
}
