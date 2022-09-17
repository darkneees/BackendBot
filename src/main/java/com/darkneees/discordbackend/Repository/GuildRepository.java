package com.darkneees.discordbackend.Repository;

import com.darkneees.discordbackend.Entity.GuildEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuildRepository extends JpaRepository<GuildEntity, Long> {
    @Query("select g.count from GuildEntity g where g.timeMessage>= 'CURDATE()' and hour(g.timeMessage)=hour(current_timestamp)")
    Optional<Long> getCountByGuildId(@Param("guildId") long guildId);
}
