package com.yeonnnnjs.click.controller;

import com.yeonnnnjs.click.data.Entity.ClickRank;
import com.yeonnnnjs.click.data.dto.GetCountDto;
import com.yeonnnnjs.click.data.dto.RankDto;
import com.yeonnnnjs.click.service.RankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class RankController {
    private final RankService rankService;

    @Autowired
    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @PostMapping("/addrank")
    public String addRank(@RequestBody RankDto rankDto) {
        return rankService.addRank(rankDto);
    }

    @GetMapping("/getrank")
    public ResponseEntity<List<ClickRank>> getRank() {
        List<ClickRank> rankDtos = rankService.getRank();
        return ResponseEntity.status(HttpStatus.OK).body(rankDtos);
    }

    @PostMapping("/getcount")
    public ResponseEntity<Long> getCount(@RequestBody GetCountDto getCountDto) {
        return ResponseEntity.status(HttpStatus.OK).body(rankService.getCount(getCountDto.getName()));
    }
}