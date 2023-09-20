package com.yeonnnnjs.click.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.yeonnnnjs.click.data.dto.EventDto;
import com.yeonnnnjs.click.data.dto.EventValue;
import com.yeonnnnjs.click.service.RedisService;
import netscape.javascript.JSObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setData(EventDto eventDto) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(eventDto.getValue());
        redisTemplate.opsForValue().set(eventDto.getKey(), jsonString);
    }

    public EventValue getData(String key){
        String value = redisTemplate.opsForValue().get(key);
        Gson gson = new Gson();

        EventValue eventValue = gson.fromJson(value, EventValue.class);
        System.out.println(eventValue);
        return eventValue;
    }

    public void deleteData(String key){
        redisTemplate.delete(key);
    }
}