package com.yeonnnnjs.click.data.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "ClickRank")
@Getter
@Setter
public class ClickRank {
    @Id
    @Column(nullable = false)
    private String playerName;
    @Column(nullable = false)
    private Long clickCount;
    @Column(nullable = false)
    private Date timeLog;
}
