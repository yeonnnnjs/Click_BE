package com.yeonnnnjs.click.controller;

import com.yeonnnnjs.click.data.dto.EventDto;
import com.yeonnnnjs.click.data.dto.RoomDto;
import com.yeonnnnjs.click.service.BattleService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class BattleController {

    private final BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @PostMapping("/makeroom")
    public void makeRoom(@RequestBody RoomDto roomDto) {
        battleService.makeRoom(roomDto);
    }

    @GetMapping("/roomlist")
    public ResponseEntity<List<RoomDto>> getRoomList() {
        List<RoomDto> roomDtos = battleService.getRoomList();
        return ResponseEntity.status(HttpStatus.OK).body(roomDtos);
    }

    @GetMapping("/{name}")
    public void deleteRoom(@PathVariable String name) {
        battleService.deleteRoom(name);
    }
}