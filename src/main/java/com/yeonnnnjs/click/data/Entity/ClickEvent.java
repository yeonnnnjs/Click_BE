package com.yeonnnnjs.click.data.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@RedisHash
@Getter
@Setter
public class ClickEvent {

    @Id
    private String name;

    private Long count;

    private Date timestamp;
}
