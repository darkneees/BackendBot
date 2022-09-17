package com.darkneees.discordbackend.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfiguration {

    @Value("${bot.token}")
    private String token;

    @Value("${spring.jpa.properties.hibernate.jdbc.time_zone}")
    private String timeZone;

    public String getDiscordToken() {
        return token;
    }

    public String getTimeZone() {
        return timeZone;
    }
}
