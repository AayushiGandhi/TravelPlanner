package com.example.travel.controller;

import com.example.travel.kafka.TravelProducer;
import com.example.travel.model.TripModel;
import com.example.travel.dto.Trip;
import com.example.travel.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {
    @Autowired
    private TripService tripService;

    private TravelProducer travelProducer;

    public TripController(TravelProducer travelProducer) {
        this.travelProducer = travelProducer;
    }

    @GetMapping
    public List<TripModel> getAllTrips() {
        return tripService.getAllTrips();
    }

    @GetMapping("/{id}")
    public TripModel getTripById(@PathVariable Long id) {
        return tripService.getTripById(id);
    }

    @PostMapping
    public TripModel createTrip(@RequestBody TripModel tripModel) {

        Trip trip = new Trip();
        trip.setId(tripModel.getId());
        trip.setDestination(tripModel.getDestination());
        trip.setStartDate(tripModel.getStartDate());
        trip.setEndDate(tripModel.getEndDate());
        trip.setDescription(tripModel.getDescription());

        travelProducer.sendMessage(trip);

        return tripService.saveTrip(tripModel);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
    }
}
