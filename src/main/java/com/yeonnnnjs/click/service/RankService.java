package com.yeonnnnjs.click.service;

import com.yeonnnnjs.click.data.Entity.ClickRank;
import com.yeonnnnjs.click.data.dto.RankDto;

import java.util.List;

public interface RankService {
    String addRank();

    List<ClickRank> getRank();

    Long getCount(String name);
}
