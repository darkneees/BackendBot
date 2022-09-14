package com.darkneees.discordbackend.Service.Bot;

import com.darkneees.discordbackend.Configuration.BotConfiguration;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.AnnotatedEventManager;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class BotServiceImpl implements BotService {

    private JDA jda;
    public BotServiceImpl(BotConfiguration botConfiguration) {
        try {
            this.jda = JDABuilder.createLight(botConfiguration.getDiscordToken(),
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.DIRECT_MESSAGES,
                            GatewayIntent.MESSAGE_CONTENT)
                    .setEventManager(new AnnotatedEventManager()).build();
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JDA getJDA() {
        return jda;
    }

    @Override
    public void shutdownBot() {
        jda.shutdown();
    }

    @Override
    public void registerListeners(Object... listeners) {
        this.jda.addEventListener(listeners);
    }
}
