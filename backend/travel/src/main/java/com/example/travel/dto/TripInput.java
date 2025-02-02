package com.example.travel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TripInput {
    private String destination;
    private String startDate;
    private String endDate;
    private String description;
}
