package com.yeonnnnjs.click.service.Impl;

import com.google.gson.Gson;
import com.yeonnnnjs.click.data.Entity.ClickEvent;
import com.yeonnnnjs.click.data.Entity.ClickRank;
import com.yeonnnnjs.click.data.dto.EventDto;
import com.yeonnnnjs.click.data.dto.EventValue;
import com.yeonnnnjs.click.data.repository.RankRepository;
import com.yeonnnnjs.click.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, String> redisTemplate;
    private final RankRepository rankRepository;

    public RedisServiceImpl(RedisTemplate<String, String> redisTemplate,
                            RankRepository rankRepository) {
        this.redisTemplate = redisTemplate;
        this.rankRepository = rankRepository;
    }

    public void setData(EventDto eventDto, String mode) {
        Gson gson = new Gson();
        ClickEvent clickEventFromRedis = gson.fromJson(redisTemplate.opsForList().index(mode+eventDto.getName(), -1), ClickEvent.class);
        Long count;
        if(mode == "rank") {
            ClickRank clickEventFromDB = rankRepository.findByPlayerName(eventDto.getName());
            count = clickEventFromRedis == null ? clickEventFromDB == null ? 0 : clickEventFromDB.getClickCount() : clickEventFromRedis.getCount()+1;
        }
        else {
            count = clickEventFromRedis == null ? 0 : clickEventFromRedis.getCount()+1;
        }

        EventValue eventValue = new EventValue();
        eventValue.setName(eventDto.getName());
        eventValue.setCount(count);
        eventValue.setTimestamp(new Date());

        String jsonString = gson.toJson(eventValue);
        redisTemplate.opsForList().rightPush(mode+eventDto.getName(), jsonString);
    }
}