package com.darkneees.discordbackend.Controller;

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
    public CompletableFuture<ResponseEntity> getBestUser(@PathVariable("id") long id)  {
        return restService.getBestMember(id).<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetGuildsFailure);
    }

    @RequestMapping("/guild/{id}/bestchannel")
    public CompletableFuture<ResponseEntity> getActiveChannel(@PathVariable ("id") long id) {
        return restService.getBestChannel(id).<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetGuildsFailure);
    }

    @RequestMapping("/guild/{id}/messagesinhour")
    public CompletableFuture<ResponseEntity> getCountMessageInHour(@PathVariable("id") long id) {
        return restService.getMessagesInHour(id).<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetGuildsFailure);
    }

    private static final Function<Throwable, ResponseEntity<? extends List<HashMap<String, String>>>>
            handleGetGuildsFailure = throwable -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
}
