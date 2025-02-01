package com.example.travel.dto;

import lombok.Data;

@Data
public class Trip {
    private Long id;
    private String destination;
    private String startDate;
    private String endDate;
    private String description;
}
