package com.yeonnnnjs.click.controller;

import com.yeonnnnjs.click.data.dto.EventDto;
import com.yeonnnnjs.click.data.dto.RankDto;
import com.yeonnnnjs.click.service.RankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/rank")
@RestController
public class RankController {
    private final RankService rankService;

    @Autowired
    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @PostMapping("/addrank")
    public Boolean addRank(@RequestBody EventDto eventDto) {
        return rankService.addRank(eventDto.getName());
    }

    @GetMapping("/getrank")
    public ResponseEntity<List<RankDto>> getRank() {
        List<RankDto> rankDtos = rankService.getRank();
        return ResponseEntity.status(HttpStatus.OK).body(rankDtos);
    }

    @PostMapping("/getcount")
    public ResponseEntity<Long> getCount(@RequestBody EventDto eventDto) {
        return ResponseEntity.status(HttpStatus.OK).body(rankService.getCount(eventDto.getName()));
    }
}