package com.github.regyl.lab4.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationWayDtoXml implements SpecializationContainer {
    
    private String specialization;
}
