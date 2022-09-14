package com.darkneees.discordbackend.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfiguration {

    @Value("${bot.token}")
    private String token;

    public String getDiscordToken() {
        return token;
    }
}
