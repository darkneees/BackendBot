package com.darkneees.discordbackend.service.dbservice.bestmember;

import com.darkneees.discordbackend.configuration.BotConfiguration;
import com.darkneees.discordbackend.entity.BestMemberEntity;
import com.darkneees.discordbackend.exception.NoEntityException;
import com.darkneees.discordbackend.repository.BestMemberRepository;
import net.dv8tion.jda.api.entities.Message;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class BestMemberServiceImpl implements BestMemberService {

    private final BestMemberRepository repository;
    private final BotConfiguration botConfiguration;

    public BestMemberServiceImpl(BestMemberRepository repository, BotConfiguration botConfiguration) {
        this.repository = repository;
        this.botConfiguration = botConfiguration;
    }


    @Override
    public Optional<BestMemberEntity> getBestMember(long UserId, long GuildId, LocalDate date) {
        return repository.getBestMemberEntitiesByUserIdAndGuildIdAndTimeMessage(UserId, GuildId, date);
    }

    @Override
    public Long getBestMemberByGuildIdAndMaxCount(long guildId) throws NoEntityException {
        return repository.getUserIdByGuildIdAndCountMax(guildId).orElseThrow(() -> new NoEntityException(String.valueOf(guildId)));
    }

    @Override
    public void updateBestMemberEntity(Message message) {

        CompletableFuture.runAsync(() -> {

            Optional<BestMemberEntity> optionalEntity = getBestMember(
                    message.getAuthor().getIdLong(),
                    message.getGuild().getIdLong(),
                    message.getTimeCreated()
                            .toZonedDateTime()
                            .withZoneSameInstant(ZoneId.of(botConfiguration.getTimeZone()))
                            .toLocalDate()
            );

            optionalEntity.ifPresentOrElse(
                            this::updateMember,
                    () -> createMember(message)
            );
        });

    }

    private void createMember(Message message){

        BestMemberEntity entity = new BestMemberEntity(
                message.getAuthor().getIdLong(),
                message.getGuild().getIdLong(),
                message.getTimeCreated()
                        .toZonedDateTime()
                        .withZoneSameInstant(ZoneId.of(botConfiguration.getTimeZone()))
                        .toLocalDate()
        );
        repository.save(entity);
    }

    private void updateMember(BestMemberEntity entity){
        entity.UpdateCount();
        repository.save(entity);
    }

}
