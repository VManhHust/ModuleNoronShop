package com.example.commons.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ActionLogDTO {
    private String objectId;
    private String objectType;
    private Long createdAt;
}
