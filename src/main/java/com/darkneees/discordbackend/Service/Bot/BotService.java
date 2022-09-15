package com.darkneees.discordbackend.Service.Bot;

import net.dv8tion.jda.api.JDA;

import java.util.HashMap;
import java.util.List;

public interface BotService {

    JDA getJDA();
    void shutdownBot();
    void registerListeners(Object... listeners);
    List<HashMap<String, String>> getAllGuilds();
}
