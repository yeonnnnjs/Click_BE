package com.yeonnnnjs.click.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yeonnnnjs.click.data.Entity.ClickRank;
import com.yeonnnnjs.click.data.dto.EventDto;
import com.yeonnnnjs.click.data.dto.EventValue;
import com.yeonnnnjs.click.data.dto.GetCountDto;
import com.yeonnnnjs.click.data.dto.GetRedisDto;
import com.yeonnnnjs.click.service.RankService;
import com.yeonnnnjs.click.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/getredis")
    public ResponseEntity<EventValue> getRedis(@RequestBody GetRedisDto getRedisDto) {
        EventValue eventValue = redisService.getData(getRedisDto.getKey());
        return ResponseEntity.status(HttpStatus.OK).body(eventValue);
    }

    @PostMapping("/setredis")
    public ResponseEntity<String> setRedis(@RequestBody EventDto eventDto) throws JsonProcessingException {
        redisService.setData(eventDto);
        return ResponseEntity.status(HttpStatus.OK).body("d");
    }

    @PostMapping("/deleteredis")
    public ResponseEntity<String> getCount(@RequestBody GetRedisDto getRedisDto) {
        redisService.deleteData(getRedisDto.getKey());
        return ResponseEntity.status(HttpStatus.OK).body("d");
    }
}
