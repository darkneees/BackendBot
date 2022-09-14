package com.darkneees.discordbackend.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "bestchannels")
public class BestChannelEntity {

    @Id
    @Column(name = "ChannelId")
    private long channelId;

    @Column(name = "GuildId")
    private long guildId;

    @Column(name = "time_message")
    private LocalDate timeMessage;

    @Column(name = "count")
    private long count;

    public BestChannelEntity() {
    }

    public BestChannelEntity(long guildId, long channelId, LocalDate timeMessage) {
        this.guildId = guildId;
        this.channelId = channelId;
        this.timeMessage = timeMessage;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public long getGuildId() {
        return guildId;
    }

    public void setGuildId(long guildId) {
        this.guildId = guildId;
    }

    public LocalDate getTimeMessage() {
        return timeMessage;
    }

    public void setTimeMessage(LocalDate timeMessage) {
        this.timeMessage = timeMessage;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void ChangeCount(){
        count = count + 1;
    }
}
