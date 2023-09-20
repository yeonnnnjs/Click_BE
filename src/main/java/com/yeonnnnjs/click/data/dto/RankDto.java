package com.yeonnnnjs.click.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RankDto {
    private String name;
    private EventDto[] clickLogs;
}
