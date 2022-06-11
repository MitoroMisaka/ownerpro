package com.ownerpro.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReferenceRequest {
    private Long article_id;
    private Long reference_id;
    private String note;
}
