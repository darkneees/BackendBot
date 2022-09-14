package com.darkneees.discordbackend.Service.DbService.BestChannel;

import com.darkneees.discordbackend.Entity.BestChannelEntity;
import com.darkneees.discordbackend.Repository.BestChannelRepository;
import net.dv8tion.jda.api.entities.Message;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BestChannelServiceImpl implements BestChannelService {

    private final BestChannelRepository repository;

    public BestChannelServiceImpl(BestChannelRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<BestChannelEntity> getBestChannelByIdAndGuildIdAndTime(long GuildId, long ChannelId, LocalDate date) {
        return repository.getBestChannelEntitiesByGuildIdAndChannelIdAndTimeMessage(GuildId, ChannelId, date);
    }

    @Override
    public void ChangeCountMessages (Message message) {
        Optional<BestChannelEntity> optionalEntity = getBestChannelByIdAndGuildIdAndTime(
                message.getGuild().getIdLong(),
                message.getChannel().getIdLong(),
                message.getTimeCreated().toLocalDate()
        );

        optionalEntity.ifPresentOrElse(
                this::UpdateChannel,
                () -> CreateChannel(message)
        );
    }

    private void CreateChannel(Message message){

        BestChannelEntity entity = new BestChannelEntity(
                message.getGuild().getIdLong(),
                message.getChannel().getIdLong(),
                message.getTimeCreated().toLocalDate()
        );
        entity.ChangeCount();
        repository.save(entity);
    }

    private void UpdateChannel(BestChannelEntity entity){
        entity.ChangeCount();
        repository.save(entity);
    }

}
