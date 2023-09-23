package com.yeonnnnjs.click.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yeonnnnjs.click.data.dto.EventDto;
import com.yeonnnnjs.click.data.dto.EventValue;

public interface RedisService {
    void setData(EventDto eventDto) throws JsonProcessingException;
}
