package com.yeonnnnjs.click.data.repository;

import com.yeonnnnjs.click.data.Entity.ClickRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<ClickRank, String> {
    ClickRank findByPlayerName(String name);

    Boolean existsByPlayerName(String name);
}
