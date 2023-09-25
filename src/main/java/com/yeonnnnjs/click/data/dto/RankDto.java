package com.yeonnnnjs.click.data.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RankDto {
    private String playerName;
    private Long clickCount;
    private String timestamp;
}
