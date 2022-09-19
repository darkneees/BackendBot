package com.darkneees.discordbackend.repository;

import com.darkneees.discordbackend.entity.BestMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BestMemberRepository extends JpaRepository<BestMemberEntity, Long> {
    Optional<BestMemberEntity> getBestMemberEntitiesByUserIdAndGuildIdAndTimeMessage(long UserId, long GuildId, LocalDate date);
    @Query("select b.userId from BestMemberEntity b where b.guildId=:ids and b.timeMessage=current_date and b.count=(select max(b1.count) from BestMemberEntity b1 where b1.timeMessage=current_date and b1.guildId=:ids)")
    Optional<Long> getUserIdByGuildIdAndCountMax(@Param("ids") long id);
}
