package com.darkneees.discordbackend.Repository;

import com.darkneees.discordbackend.Entity.BestMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BestMemberRepository extends JpaRepository<BestMemberEntity, Long> {

    Optional<BestMemberEntity> getBestMemberEntitiesByUserIdAndGuildIdAndTimeMessage(long UserId, long GuildId, LocalDate date);
    @Query("select b.userId from BestMemberEntity b where b.guildId=:ids and b.count=(select max(d.count) from BestMemberEntity d)")
    long getUserIdByGuildIdAndCountMax(@Param("ids") long id);
}
