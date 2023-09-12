package com.yeonnnnjs.click.data.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RankDto {
    private String name;
    private Long count;
    private Date timestamp;
}
