package com.darkneees.discordbackend.Service.DbService.BestMember;

import com.darkneees.discordbackend.Entity.BestMemberEntity;
import com.darkneees.discordbackend.Repository.BestMemberRepository;
import net.dv8tion.jda.api.entities.Message;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BestMemberServiceImpl implements BestMemberService {

    private final BestMemberRepository repository;

    public BestMemberServiceImpl(BestMemberRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<BestMemberEntity> getBestMemberByIdAndGuildIdAndTime(long GuildId, long UserId, LocalDate date) {
        return repository.getBestMemberEntitiesByGuildIdAndUserIdAndTimeMessage(GuildId, UserId, date);
    }

    @Override
    public void ChangeCountMessages (Message message) {

        Optional<BestMemberEntity> optionalEntity = getBestMemberByIdAndGuildIdAndTime(
                message.getGuild().getIdLong(),
                message.getAuthor().getIdLong(),
                message.getTimeCreated().toLocalDate()
        );

        optionalEntity.ifPresentOrElse(
                this::UpdateMember,
                () -> CreateMember(message)
        );

    }

    private void CreateMember(Message message){

        BestMemberEntity entity = new BestMemberEntity(
                message.getGuild().getIdLong(),
                message.getAuthor().getIdLong(),
                message.getTimeCreated().toLocalDate()
        );
        entity.ChangeCount();
        repository.save(entity);
    }

    private void UpdateMember(BestMemberEntity entity){
        entity.ChangeCount();
        repository.save(entity);
    }

}
