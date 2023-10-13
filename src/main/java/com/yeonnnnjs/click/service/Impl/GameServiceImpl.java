package com.yeonnnnjs.click.service.Impl;

import com.google.gson.Gson;
import com.yeonnnnjs.click.data.Entity.ClickEvent;
import com.yeonnnnjs.click.service.GameService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    private final RedisTemplate redisTemplate;

    public GameServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Long getScore(String name) {
        Gson gson = new Gson();
        ClickEvent clickEvent = gson.fromJson(redisTemplate.opsForList().index("game"+name, -1).toString(), ClickEvent.class);

        if(clickEvent == null) {
            return 0L;
        }
        else {
            return clickEvent.getCount();
        }
    }
}