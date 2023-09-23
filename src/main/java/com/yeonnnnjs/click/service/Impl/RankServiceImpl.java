package com.yeonnnnjs.click.service.Impl;

import com.google.gson.Gson;
import com.yeonnnnjs.click.data.Entity.ClickEvent;
import com.yeonnnnjs.click.data.Entity.ClickRank;
import com.yeonnnnjs.click.data.repository.RankRepository;
import com.yeonnnnjs.click.service.RankService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class RankServiceImpl implements RankService {
    private final RankRepository rankRepository;
    private final RedisTemplate redisTemplate;

    public RankServiceImpl(RankRepository rankRepository, RedisTemplate redisTemplate) {
        this.rankRepository = rankRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Boolean addRank(String name) {
        Gson gson = new Gson();
        ClickEvent clickEvent = gson.fromJson(redisTemplate.opsForList().index(name, -1).toString(), ClickEvent.class);

        if(clickEvent == null) {
            return false;
        }
        else {
            ClickRank rank = new ClickRank();
            rank.setClickCount(clickEvent.getCount());
            rank.setPlayerName(clickEvent.getName());
            rank.setTimeLog(clickEvent.getTimestamp());
            rankRepository.save(rank);
            return true;
        }
    }

    @Override
    public List<ClickRank> getRank() {
        List<ClickRank> ranks = rankRepository.findAll();
        ranks.sort(
                Comparator.comparing(ClickRank::getClickCount)
                        .reversed()
                        .thenComparing(ClickRank::getTimeLog)
        );
        return ranks;
    }

    @Override
    public Long getCount(String name) {
        ClickRank clickRank = rankRepository.findByPlayerName(name);
        if(clickRank == null) return 0L;
        else {
            return clickRank.getClickCount();
        }
    }
}