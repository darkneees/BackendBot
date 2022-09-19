package com.darkneees.discordbackend.repository;

import com.darkneees.discordbackend.entity.BestChannelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BestChannelRepository extends JpaRepository<BestChannelEntity, Long> {

    Optional<BestChannelEntity> getBestChannelEntitiesByChannelIdAndGuildIdAndTimeMessage(long ChannelId, long GuildId, LocalDate date);
    @Query("select b.channelId from BestChannelEntity b where b.guildId=:ids and b.timeMessage=current_date and b.count=(select max(b1.count) from BestChannelEntity b1 where b1.timeMessage=current_date and b1.guildId=:ids)")
    Optional<Long> getChannelIdByGuildIdAndCountMax(@Param("ids") long id);

}
