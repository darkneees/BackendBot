package com.darkneees.discordbackend.Repository;

import com.darkneees.discordbackend.Entity.BestMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BestMemberRepository extends JpaRepository<BestMemberEntity, Long> {

    Optional<BestMemberEntity> getBestMemberEntitiesByGuildIdAndUserIdAndTimeMessage(long GuildId, long UserId, LocalDate date);
}
