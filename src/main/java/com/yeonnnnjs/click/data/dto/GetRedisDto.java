package com.yeonnnnjs.click.data.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GetRedisDto {
    private String key;
}
