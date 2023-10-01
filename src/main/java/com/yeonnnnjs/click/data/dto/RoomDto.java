package com.yeonnnnjs.click.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RoomDto {
    private String name;
    private String title;
    private String timestamp;
}
