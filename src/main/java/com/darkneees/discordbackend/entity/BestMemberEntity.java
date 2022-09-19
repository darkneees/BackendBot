package com.darkneees.discordbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "bestmembers")
public class BestMemberEntity {

    @Id
    @Column(name = "GuildId")
    private long guildId;

    @Column(name = "UserId")
    private long userId;

    @Column(name = "time_message")
    private LocalDate timeMessage;

    @Column(name = "count")
    private long count;

    public BestMemberEntity() {
    }

    public BestMemberEntity(long userId, long guildId, LocalDate timeMessage) {
        this.userId = userId;
        this.guildId = guildId;
        this.timeMessage = timeMessage;
        ++count;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getTimeMessage() {
        return timeMessage;
    }

    public void setTimeMessage(LocalDate timeMessage) {
        this.timeMessage = timeMessage;
    }

    public void UpdateCount(){
        ++count;
    }

    public long getGuildId() {
        return guildId;
    }

    public void setGuildId(long guildId) {
        this.guildId = guildId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
