package com.yeonnnnjs.click.data.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class EventValue {
    String name;
    Long count;
    Date timestamp;
}
