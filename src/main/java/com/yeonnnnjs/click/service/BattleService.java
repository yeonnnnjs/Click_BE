package com.yeonnnnjs.click.service;

import com.yeonnnnjs.click.data.dto.RoomDto;

import java.util.List;

public interface BattleService {

    void makeRoom(RoomDto roomDto);

    List<RoomDto> getRoomList();

    void deleteRoom(String name);
}
