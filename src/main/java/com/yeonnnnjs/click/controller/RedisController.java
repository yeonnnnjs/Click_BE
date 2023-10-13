package com.yeonnnnjs.click.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yeonnnnjs.click.data.dto.EventDto;
import com.yeonnnnjs.click.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RedisController {
    private final RedisService redisService;

    @Autowired
    public RedisController (RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping("/rank/setredis")
    public ResponseEntity<String> setRedisForRank(@RequestBody EventDto eventDto) throws JsonProcessingException {
        redisService.setData(eventDto, "rank");
        return ResponseEntity.status(HttpStatus.OK).body("set data in redis!");
    }

    @PostMapping("/game/setredis")
    public ResponseEntity<String> setRedisForGame(@RequestBody EventDto eventDto) throws JsonProcessingException {
        redisService.setData(eventDto, "game");
        return ResponseEntity.status(HttpStatus.OK).body("set data in redis!");
    }
}
