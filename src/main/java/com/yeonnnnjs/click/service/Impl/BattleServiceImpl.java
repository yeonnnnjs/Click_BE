package com.yeonnnnjs.click.service.Impl;

import com.yeonnnnjs.click.data.Entity.Battles;
import com.yeonnnnjs.click.data.Entity.ClickRank;
import com.yeonnnnjs.click.data.dto.RankDto;
import com.yeonnnnjs.click.data.dto.RoomDto;
import com.yeonnnnjs.click.data.repository.BattlesRepository;
import com.yeonnnnjs.click.service.BattleService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class BattleServiceImpl implements BattleService {
    private final BattlesRepository battlesRepository;

    public BattleServiceImpl(BattlesRepository battlesRepository) {
        this.battlesRepository = battlesRepository;
    }

    public void makeRoom(RoomDto roomDto) {
        if(battlesRepository.existsById(roomDto.getName())) {
            battlesRepository.deleteById(roomDto.getName());
        }
        Battles battles = new Battles();
        battles.setPlayerName(roomDto.getName());
        battles.setTitle(roomDto.getTitle());
        battles.setTimestamp(new Date());
        battlesRepository.save(battles);
    }

    public List<RoomDto> getRoomList() {
        List<Battles> battlesList = battlesRepository.findAll();
        List<RoomDto> roomDtos = new ArrayList<>();

        for (Battles battles : battlesList) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = dateFormat.format(battles.getTimestamp());
            RoomDto roomDto = new RoomDto();
            roomDto.setTitle(battles.getTitle());
            roomDto.setName(battles.getPlayerName());
            roomDto.setTimestamp(timestamp);
            roomDtos.add(roomDto);
        }
        return roomDtos;
    }

    public void deleteRoom(String name) {
        System.out.println(name);
        battlesRepository.deleteById(name);
    }
}