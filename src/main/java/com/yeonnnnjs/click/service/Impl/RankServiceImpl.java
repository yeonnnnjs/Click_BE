package com.yeonnnnjs.click.service.Impl;

import com.google.gson.Gson;
import com.yeonnnnjs.click.data.Entity.ClickEvent;
import com.yeonnnnjs.click.data.Entity.ClickRank;
import com.yeonnnnjs.click.data.dto.RankDto;
import com.yeonnnnjs.click.data.repository.RankRepository;
import com.yeonnnnjs.click.service.RankService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class RankServiceImpl implements RankService {
    private final RankRepository rankRepository;
    private final RedisTemplate redisTemplate;

    public RankServiceImpl(RankRepository rankRepository, RedisTemplate redisTemplate) {
        this.rankRepository = rankRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void addRank(String name) {
        Gson gson = new Gson();
        ClickEvent clickEvent = gson.fromJson(redisTemplate.opsForList().index("rank"+name, -1).toString(), ClickEvent.class);

        if(clickEvent != null) {
            ClickRank rank = new ClickRank();
            rank.setClickCount(clickEvent.getCount());
            rank.setPlayerName(clickEvent.getName());
            rank.setTimeLog(clickEvent.getTimestamp());
            rankRepository.save(rank);
        }
    }

    @Override
    public List<RankDto> getRank() {
        List<ClickRank> ranks = rankRepository.findAll();
        List<RankDto> rankDtos = new ArrayList<>();
        ranks.sort(
                Comparator.comparing(ClickRank::getClickCount)
                        .reversed()
                        .thenComparing(ClickRank::getTimeLog)
        );

        for (ClickRank clickRank : ranks) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = dateFormat.format(clickRank.getTimeLog());
            RankDto rankDto = new RankDto();
            rankDto.setPlayerName(clickRank.getPlayerName());
            rankDto.setClickCount(clickRank.getClickCount());
            rankDto.setTimestamp(timestamp);
            rankDtos.add(rankDto);
        }

        return rankDtos;
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