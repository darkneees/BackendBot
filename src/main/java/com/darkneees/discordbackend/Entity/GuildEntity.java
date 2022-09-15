package com.darkneees.discordbackend.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name = "guilds")
public class GuildEntity {

    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "time_message")
    private ZonedDateTime timeMessage;
    @Column(name = "count")
    private int count;

    public GuildEntity() {
    }

    public GuildEntity(long id, ZonedDateTime timeMessage) {
        this.id = id;
        this.timeMessage = timeMessage;
        ++count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ZonedDateTime getTimeMessage() {
        return timeMessage;
    }

    public void setTimeMessage(ZonedDateTime timeMessage) {
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
