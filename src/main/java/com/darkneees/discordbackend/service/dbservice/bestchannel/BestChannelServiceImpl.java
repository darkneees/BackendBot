package com.darkneees.discordbackend.service.dbservice.bestchannel;

import com.darkneees.discordbackend.configuration.BotConfiguration;
import com.darkneees.discordbackend.entity.BestChannelEntity;
import com.darkneees.discordbackend.exception.NoEntityException;
import com.darkneees.discordbackend.repository.BestChannelRepository;
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
    public Optional<BestChannelEntity> getBestChannel(long ChannelId, long GuildId, LocalDate date) {
        return repository.getBestChannelEntitiesByChannelIdAndGuildIdAndTimeMessage(ChannelId, GuildId, date);
    }

    @Override
    public Long getBestChannelByGuildIdAndMaxCount(long guildId) throws NoEntityException {
        return repository.getChannelIdByGuildIdAndCountMax(guildId).orElseThrow(() -> new NoEntityException(String.valueOf(guildId)));
    }

    @Override
    public void updateBestChannelEntity(Message message) {

        CompletableFuture.runAsync(() -> {
            Optional<BestChannelEntity> optionalEntity = getBestChannel(
                    message.getChannel().getIdLong(),
                    message.getGuild().getIdLong(),
                    message.getTimeCreated()
                            .toZonedDateTime()
                            .withZoneSameInstant(ZoneId.of(botConfiguration.getTimeZone()))
                            .toLocalDate()
            );

            optionalEntity.ifPresentOrElse(
                    this::updateChannel,
                    () -> createChannel(message)
            );
        });
    }

    private void createChannel(Message message){

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

    private void updateChannel(BestChannelEntity entity){
        entity.UpdateCount();
        repository.save(entity);
    }

}
