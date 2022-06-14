package com.ownerpro.web.controller.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriorityRequest {
    private Long id;
    private Integer select;
    private Integer update;
    private Integer delete;
    private Integer insert;
}
