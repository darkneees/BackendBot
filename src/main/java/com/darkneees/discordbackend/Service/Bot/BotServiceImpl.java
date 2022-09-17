package com.darkneees.discordbackend.Service.Bot;

import com.darkneees.discordbackend.Configuration.BotConfiguration;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.AnnotatedEventManager;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@Service
public class BotServiceImpl implements BotService {

    private JDA jda;
    private BotConfiguration botConfiguration;
    public BotServiceImpl(BotConfiguration botConfiguration) {
        this.botConfiguration = botConfiguration;
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
    public void startBot() {
        try {
            this.jda = JDABuilder.createLight(botConfiguration.getDiscordToken(),
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.DIRECT_MESSAGES,
                            GatewayIntent.MESSAGE_CONTENT)
                    .setEventManager(new AnnotatedEventManager()).build();
            System.out.println(botConfiguration.getTimeZone());
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registerListeners(Object... listeners) {
        this.jda.addEventListener(listeners);
    }


}
