package com.darkneees.discordbackend.Service.DbService.BestChannel;

import com.darkneees.discordbackend.Configuration.BotConfiguration;
import com.darkneees.discordbackend.Entity.BestChannelEntity;
import com.darkneees.discordbackend.Exception.NoEntityException;
import com.darkneees.discordbackend.Repository.BestChannelRepository;
import net.dv8tion.jda.api.entities.Message;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class BestChannelServiceImpl implements BestChannelService {

    private final BestChannelRepository repository;
    private final BotConfiguration botConfiguration;

    public BestChannelServiceImpl(BestChannelRepository repository, BotConfiguration botConfiguration) {
        this.repository = repository;
        this.botConfiguration = botConfiguration;
    }

    @Override
    public Optional<BestChannelEntity> getBestChannelByIdAndGuildIdAndTime(long ChannelId, long GuildId, LocalDate date) {
        return repository.getBestChannelEntitiesByChannelIdAndGuildIdAndTimeMessage(ChannelId, GuildId, date);
    }

    @Override
    public Long getBestChannelByGuildIdAndMaxCount(long guildId) throws NoEntityException {
        return repository.getChannelIdByGuildIdAndCountMax(guildId).orElseThrow(() -> new NoEntityException(String.valueOf(guildId)));
    }

    @Override
    public void ChangeCountMessages (Message message) {

        CompletableFuture.runAsync(() -> {
            Optional<BestChannelEntity> optionalEntity = getBestChannelByIdAndGuildIdAndTime(
                    message.getChannel().getIdLong(),
                    message.getGuild().getIdLong(),
                    message.getTimeCreated()
                            .toZonedDateTime()
                            .withZoneSameInstant(ZoneId.of(botConfiguration.getTimeZone()))
                            .toLocalDate()
            );

            optionalEntity.ifPresentOrElse(
                    this::UpdateChannel,
                    () -> CreateChannel(message)
            );
        });
    }

    private void CreateChannel(Message message){

        BestChannelEntity entity = new BestChannelEntity(
                message.getChannel().getIdLong(),
                message.getGuild().getIdLong(),
                message.getTimeCreated()
                        .toZonedDateTime()
                        .withZoneSameInstant(ZoneId.of(botConfiguration.getTimeZone()))
                        .toLocalDate()
        );
        repository.save(entity);
    }

    private void UpdateChannel(BestChannelEntity entity){
        entity.UpdateCount();
        repository.save(entity);
    }

}
