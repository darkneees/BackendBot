package com.darkneees.discordbackend.service.bot;

import net.dv8tion.jda.api.JDA;

public interface BotService {

    void startBot();
    JDA getJDA();
    void shutdownBot();
    void registerListeners(Object... listeners);


}
