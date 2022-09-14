package com.darkneees.discordbackend.Repository;

import com.darkneees.discordbackend.Entity.GuildEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuildRepository extends JpaRepository<GuildEntity, Long> {
}
