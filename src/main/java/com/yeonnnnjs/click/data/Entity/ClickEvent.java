package com.yeonnnnjs.click.data.Entity;

import com.yeonnnnjs.click.data.dto.EventValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
@Getter
@Setter
public class ClickEvent {

    @Id
    private String key;

    private EventValue value;
}
