package com.darkneees.discordbackend.Service.Bot;

import net.dv8tion.jda.api.JDA;

public interface BotService {

    JDA getJDA();
    void shutdownBot();
    void registerListeners(Object... listeners);
}