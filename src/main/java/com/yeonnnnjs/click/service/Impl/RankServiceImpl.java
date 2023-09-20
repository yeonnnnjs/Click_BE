package com.yeonnnnjs.click.service.Impl;

import com.yeonnnnjs.click.data.Entity.ClickRank;
import com.yeonnnnjs.click.data.dto.EventDto;
import com.yeonnnnjs.click.data.dto.RankDto;
import com.yeonnnnjs.click.data.repository.RankRepository;
import com.yeonnnnjs.click.service.RankService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class RankServiceImpl implements RankService {
    private final RankRepository rankRepository;

    public RankServiceImpl(RankRepository rankRepository) {
        this.rankRepository = rankRepository;
    }

    @Override
    public String addRank() {
        ClickRank rank = new ClickRank();
        rankRepository.save(rank);
        return "Success";
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
        System.out.println(name);
        if(!rankRepository.existsByPlayerName(name)) return -1L;
        else {
            ClickRank clickRank = rankRepository.findByPlayerName(name);
            return clickRank.getClickCount();
        }
    }
}