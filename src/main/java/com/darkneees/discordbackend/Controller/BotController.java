package com.darkneees.discordbackend.Controller;

import com.darkneees.discordbackend.Service.Bot.BotServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class BotController {

    private final BotServiceImpl botService;

    public BotController(BotServiceImpl botService) {
        this.botService = botService;
    }

    @RequestMapping("/guilds")
    public List<HashMap<String, String>> getAllGuids(){
        return botService.getAllGuilds();
    }

    @RequestMapping("/guild/{id}/bestmember")
    public ResponseEntity<?> getBestUser(@PathVariable("id") String id)  {
        return null;
    }

    @RequestMapping("/guild/{id}/bestchannel")
    public ResponseEntity<?> getActiveChannel(@PathVariable ("id") String id) {
        return null;
    }

    @RequestMapping("/guild/{id}/messagesinhour")
    public ResponseEntity<?> getCountMessageInHour(@PathVariable("id") String id) {
        return null;
    }
}
