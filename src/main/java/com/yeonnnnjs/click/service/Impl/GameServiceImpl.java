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
        Object object = redisTemplate.opsForList().index("game"+name, -1);
        Gson gson = new Gson();

        if(object == null) {
            return 0L;
        }
        else {
            ClickEvent clickEvent = gson.fromJson(object.toString(), ClickEvent.class);
            redisTemplate.delete("game"+name);
            return clickEvent.getCount();
        }
    }
}