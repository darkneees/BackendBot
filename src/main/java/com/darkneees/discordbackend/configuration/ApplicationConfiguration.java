package com.darkneees.discordbackend.configuration;

import com.darkneees.discordbackend.service.bot.BotService;
import com.darkneees.discordbackend.service.bot.BotServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    private final BotConfiguration botConfiguration;

    public ApplicationConfiguration(BotConfiguration botConfiguration) {
        this.botConfiguration = botConfiguration;
    }

    @Bean
    public BotService botService(){
        return new BotServiceImpl(botConfiguration);
    }

}
