package com.example.travel.dto;

public class TripEvent {
    private String message;
    private String status;
    private Trip trip;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public TripEvent(String message, String status, Trip trip) {
        this.message = message;
        this.status = status;
        this.trip = trip;
    }

    public TripEvent() {
    }
}
