package com.darkneees.discordbackend.service.DbService.Guild;

import com.darkneees.discordbackend.configuration.BotConfiguration;
import com.darkneees.discordbackend.entity.GuildEntity;
import com.darkneees.discordbackend.exception.NoEntityException;
import com.darkneees.discordbackend.repository.GuildRepository;
import net.dv8tion.jda.api.entities.Message;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class GuildServiceImpl implements GuildService {

    private final GuildRepository repository;
    private final BotConfiguration botConfiguration;

    public GuildServiceImpl(GuildRepository repository, BotConfiguration botConfiguration) {
        this.repository = repository;
        this.botConfiguration = botConfiguration;
    }

    @Override
    public void updateGuildEntity(Message message) {

        CompletableFuture.runAsync(() -> {
            Optional<GuildEntity> optionalEntity = getGuildById(message.getGuild().getIdLong());
            optionalEntity.ifPresentOrElse(
                    this::UpdateMessages,
                    () -> CreateMessages(message)
            );
        });
    }
    private void UpdateMessages(GuildEntity guildEntity) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(botConfiguration.getTimeZone()));
        if(!guildEntity
                .getTimeMessage()
                .toLocalDate().isEqual(now.toLocalDate()) ||
                now.getHour() - guildEntity
                        .getTimeMessage()
                        .getHour() != 0) {
            guildEntity.setTimeMessage(now);
            guildEntity.setCount(1);
        } else guildEntity.UpdateCount();
        repository.save(guildEntity);
    }

    private void CreateMessages(Message message){
        GuildEntity entity = new GuildEntity(
                message.getGuild().getIdLong(),
                message.getTimeCreated().toZonedDateTime().withZoneSameInstant(ZoneId.of(botConfiguration.getTimeZone()))
        );
        repository.save(entity);
    }

    @Override
    public Optional<GuildEntity> getGuildById(long id) {
        return repository.findById(id);
    }

    @Override
    public Long getCountByGuildId(long id) throws NoEntityException {
        return repository.getCountByGuildIdAndDate(id).orElseThrow(() -> new NoEntityException(String.valueOf(id)));
    }


}
