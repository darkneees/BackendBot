package com.darkneees.discordbackend.repository;

import com.darkneees.discordbackend.entity.GuildEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuildRepository extends JpaRepository<GuildEntity, Long> {
    @Query("select g.count from GuildEntity g where g.id=:ids and date(g.timeMessage)=current_date and hour(g.timeMessage)=hour(current_timestamp)")
    Optional<Long> getCountByGuildIdAndDate(@Param("ids") long guildId);
}
