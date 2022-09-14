package com.darkneees.discordbackend.Configuration;

import com.darkneees.discordbackend.Service.Bot.BotService;
import com.darkneees.discordbackend.Service.Bot.BotServiceImpl;
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
