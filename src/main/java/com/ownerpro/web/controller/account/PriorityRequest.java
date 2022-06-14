package com.ownerpro.web.controller.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriorityRequest {
    private Long id;
    private Integer select_set;
    private Integer update_set;
    private Integer delete_set;
    private Integer insert_set;
}
