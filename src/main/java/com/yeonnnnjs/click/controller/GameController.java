package com.yeonnnnjs.click.controller;

import com.yeonnnnjs.click.data.dto.EventDto;
import com.yeonnnnjs.click.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;

@Slf4j
@RequestMapping("/api/game")
@RestController
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/getscore")
    public ResponseEntity<Long> getScore(@RequestBody EventDto eventDto) {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getScore(eventDto.getName()));
    }
}