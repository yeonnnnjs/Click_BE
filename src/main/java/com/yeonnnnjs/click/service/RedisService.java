package com.yeonnnnjs.click.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yeonnnnjs.click.data.dto.EventDto;

public interface RedisService {
    void setData(EventDto eventDto, String mode) throws JsonProcessingException;
}
