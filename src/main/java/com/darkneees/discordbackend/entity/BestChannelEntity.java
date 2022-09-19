package com.darkneees.discordbackend.entity;

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

    @Column(name = "guildId")
    private long guildId;

    @Column(name = "time_message")
    private LocalDate timeMessage;

    @Column(name = "count")
    private int count;

    public BestChannelEntity() {
    }

    public BestChannelEntity(long channelId, long guildId, LocalDate timeMessage) {
        this.channelId = channelId;
        this.guildId = guildId;
        this.timeMessage = timeMessage;
        ++count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void UpdateCount(){
        ++count;
    }
}
