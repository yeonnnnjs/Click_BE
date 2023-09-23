package com.yeonnnnjs.click.service;

import com.yeonnnnjs.click.data.Entity.ClickRank;

import java.util.List;

public interface RankService {
    Boolean addRank(String name);

    List<ClickRank> getRank();

    Long getCount(String name);
}
