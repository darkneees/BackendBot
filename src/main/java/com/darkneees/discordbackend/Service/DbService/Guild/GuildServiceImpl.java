package com.darkneees.discordbackend.Service.DbService.Guild;

import com.darkneees.discordbackend.Entity.GuildEntity;
import com.darkneees.discordbackend.Repository.GuildRepository;
import net.dv8tion.jda.api.entities.Message;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class GuildServiceImpl implements GuildService {

    private final GuildRepository repository;

    public GuildServiceImpl(GuildRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addMessageInHour(Message message) {

        CompletableFuture.runAsync(() -> {
            Optional<GuildEntity> optionalEntity = getGuildById(message.getGuild().getIdLong());

            optionalEntity.ifPresentOrElse(
                    this::UpdateMessages,
                    () -> CreateMessages(message)
            );
        });

    }

    private void UpdateMessages(GuildEntity guildEntity) {
        guildEntity.UpdateCount();
        repository.save(guildEntity);
    }


    private void CreateMessages(Message message){
        GuildEntity entity = new GuildEntity(
                message.getGuild().getIdLong(),
                ZonedDateTime.from(message.getTimeCreated())
        );
        repository.save(entity);
    }

    @Override
    public Optional<GuildEntity> getGuildById(long id) {
        return repository.findById(id);
    }


}
