package com.ownerpro.web.controller.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatisticResponse {
    private Long value;
    private String name;
}
