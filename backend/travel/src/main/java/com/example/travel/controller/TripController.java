package com.example.travel.controller;

import com.example.travel.dto.TripInput;
import com.example.travel.kafka.TravelProducer;
import com.example.travel.model.TripModel;
import com.example.travel.dto.Trip;
import com.example.travel.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/api/trips")
@Controller
public class TripController {
    @Autowired
    private TripService tripService;

    private TravelProducer travelProducer;

    public TripController(TravelProducer travelProducer) {
        this.travelProducer = travelProducer;
    }

    //@GetMapping
    @QueryMapping("allTrips")
    public List<TripModel> getAllTrips() {
        return tripService.getAllTrips();
    }

    //@GetMapping("/{id}")
    @QueryMapping("getTrip")
    public TripModel getTripById(@Argument Long id) {
        return tripService.getTripById(id);
    }
//    public TripModel getTripById(@PathVariable Long id) {
//        return tripService.getTripById(id);
//    }

    //@PostMapping
    @MutationMapping("createTrip")
    public TripModel createTrip(@Argument TripInput trip) {
//    public TripModel createTrip(@RequestBody TripModel tripModel) {

        Trip tripdto = new Trip();
        //tripdto.setId(trip.getId());
        tripdto.setDestination(trip.getDestination());
        tripdto.setStartDate(trip.getStartDate());
        tripdto.setEndDate(trip.getEndDate());
        tripdto.setDescription(trip.getDescription());

        travelProducer.sendMessage(tripdto);

        TripModel tripModel = new TripModel();
        tripModel.setDestination(trip.getDestination());
        tripModel.setStartDate(trip.getStartDate());
        tripModel.setEndDate(trip.getEndDate());
        tripModel.setDescription(trip.getDescription());

        return tripService.saveTrip(tripModel);
    }

    //@DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
    }
}
