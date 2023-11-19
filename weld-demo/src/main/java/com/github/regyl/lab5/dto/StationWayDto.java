package com.github.regyl.lab5.dto;

import com.github.regyl.lab5.annotation.IncludeElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IncludeElement
public class StationWayDto {
    
    private int specialization;
}
