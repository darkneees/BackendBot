package com.darkneees.discordbackend.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "bestmembers")
public class BestMemberEntity {

    @Id
    @Column(name = "UserId")
    private long userId;

    @Column(name = "GuildId")
    private long guildId;

    @Column(name = "TimeMessage")
    private LocalDate timeMessage;

    @Column(name = "count")
    private int count;

    public BestMemberEntity() {
    }

    public BestMemberEntity(long guildId, long userId, LocalDate timeMessage) {
        this.guildId = guildId;
        this.userId = userId;
        this.timeMessage = timeMessage;
    }

    public long getGuildId() {
        return guildId;
    }

    public void setGuildId(long guildId) {
        this.guildId = guildId;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void ChangeCount(){
        count = count + 1;
    }

    @Override
    public String toString() {
        return "BestMemberEntity{" +
                "guildId=" + guildId +
                ", userId=" + userId +
                ", timeMessage=" + timeMessage +
                ", count=" + count +
                '}';
    }
}
