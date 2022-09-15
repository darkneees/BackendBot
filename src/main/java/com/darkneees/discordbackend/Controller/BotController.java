package com.darkneees.discordbackend.Controller;

import com.darkneees.discordbackend.Service.Bot.BotServiceImpl;
import com.darkneees.discordbackend.Service.RestService.RestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@RestController
public class BotController {

    private final RestService restService;

    public BotController(RestService restService) {
        this.restService = restService;
    }

    @RequestMapping("/guilds")
    public @ResponseBody CompletableFuture<ResponseEntity> getAllGuids() {
        return restService.getAllGuilds().<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetGuildsFailure);
    }

    @RequestMapping("/guild/{id}/bestmember")
    public CompletableFuture<ResponseEntity>getBestUser(@PathVariable("id") long id)  {
        return restService.getBestMember(id).<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetGuildsFailure);
    }

    @RequestMapping("/guild/{id}/bestchannel")
    public ResponseEntity<?> getActiveChannel(@PathVariable ("id") String id) {
        return null;
    }

    @RequestMapping("/guild/{id}/messagesinhour")
    public ResponseEntity<?> getCountMessageInHour(@PathVariable("id") String id) {
        return null;
    }

    private static final Function<Throwable, ResponseEntity<? extends List<HashMap<String, String>>>>
            handleGetGuildsFailure = throwable -> {
        System.out.println(throwable.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };
}
