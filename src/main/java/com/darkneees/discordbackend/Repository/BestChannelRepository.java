package com.darkneees.discordbackend.Repository;

import com.darkneees.discordbackend.Entity.BestChannelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BestChannelRepository extends JpaRepository<BestChannelEntity, Long> {

    Optional<BestChannelEntity> getBestChannelEntitiesByChannelIdAndGuildIdAndTimeMessage(long ChannelId, long GuildId, LocalDate date);

}
