package com.yeonnnnjs.click.service.Impl;

import com.google.gson.Gson;
import com.yeonnnnjs.click.data.Entity.ClickEvent;
import com.yeonnnnjs.click.data.dto.EventDto;
import com.yeonnnnjs.click.data.dto.EventValue;
import com.yeonnnnjs.click.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setData(EventDto eventDto) {
        Gson gson = new Gson();
        ClickEvent clickEvent = gson.fromJson(redisTemplate.opsForList().index(eventDto.getName(), -1), ClickEvent.class);

        Long count = clickEvent == null ? 0 : clickEvent.getCount()+1;
        EventValue eventValue = new EventValue();
        eventValue.setName(eventDto.getName());
        eventValue.setCount(count);
        eventValue.setTimestamp(new Date());

        String jsonString = gson.toJson(eventValue);
        redisTemplate.opsForList().rightPush(eventDto.getName(), jsonString);
    }
}