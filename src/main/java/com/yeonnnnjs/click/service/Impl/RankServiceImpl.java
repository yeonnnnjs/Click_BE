package com.yeonnnnjs.click.service.Impl;

import com.yeonnnnjs.click.data.Entity.ClickRank;
import com.yeonnnnjs.click.data.dto.EventValue;
import com.yeonnnnjs.click.data.repository.RankRepository;
import com.yeonnnnjs.click.service.RankService;
import com.yeonnnnjs.click.service.RedisService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class RankServiceImpl implements RankService {
    private final RankRepository rankRepository;
    private final RedisService redisService;

    public RankServiceImpl(RankRepository rankRepository, RedisService redisService) {
        this.rankRepository = rankRepository;
        this.redisService = redisService;
    }

    @Override
    public Boolean addRank(String key) {
        EventValue eventValue = redisService.getData(key);
        if(eventValue == null) {
            return false;
        }
        else {
            ClickRank rank = new ClickRank();
            rank.setClickCount(eventValue.getCount());
            rank.setPlayerName(eventValue.getName());
            rank.setTimeLog(eventValue.getTimestamp());
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
        if(clickRank == null) return -1L;
        else {
            return clickRank.getClickCount();
        }
    }
}