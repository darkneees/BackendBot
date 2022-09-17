package com.darkneees.discordbackend.Service.DbService.BestMember;

import com.darkneees.discordbackend.Configuration.BotConfiguration;
import com.darkneees.discordbackend.Entity.BestMemberEntity;
import com.darkneees.discordbackend.Exception.NoEntityException;
import com.darkneees.discordbackend.Repository.BestMemberRepository;
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
    public Optional<BestMemberEntity> getBestMemberByIdAndGuildIdAndTime(long UserId, long GuildId, LocalDate date) {
        return repository.getBestMemberEntitiesByUserIdAndGuildIdAndTimeMessage(UserId, GuildId, date);
    }

    @Override
    public Long getBestMemberByGuildIdAndMaxCount(long guildId) throws NoEntityException {
        return repository.getUserIdByGuildIdAndCountMax(guildId).orElseThrow(() -> new NoEntityException(String.valueOf(guildId)));
    }

    @Override
    public void ChangeCountMessages (Message message) {

        System.out.println("bestmember");
        System.out.println(message.getTimeCreated());
        System.out.println(message.getTimeCreated()
                .toZonedDateTime()
                .withZoneSameInstant(ZoneId.of(botConfiguration.getTimeZone()))
                .toLocalDate());


        CompletableFuture.runAsync(() -> {

            System.out.println("bestmember");
            System.out.println(message.getTimeCreated()
                    .toZonedDateTime()
                    .withZoneSameInstant(ZoneId.of(botConfiguration.getTimeZone()))
                    .toLocalDate());

            Optional<BestMemberEntity> optionalEntity = getBestMemberByIdAndGuildIdAndTime(
                    message.getAuthor().getIdLong(),
                    message.getGuild().getIdLong(),
                    message.getTimeCreated()
                            .toZonedDateTime()
                            .withZoneSameInstant(ZoneId.of(botConfiguration.getTimeZone()))
                            .toLocalDate()
            );

            optionalEntity.ifPresentOrElse(
                            this::UpdateMember,
                    () -> CreateMember(message)
            );
        });

    }

    private void CreateMember(Message message){

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

    private void UpdateMember(BestMemberEntity entity){
        entity.UpdateCount();
        repository.save(entity);
    }

}
