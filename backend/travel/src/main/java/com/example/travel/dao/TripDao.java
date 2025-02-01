package com.example.travel.dao;

import com.example.travel.model.TripModel;
import com.example.travel.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class TripDao {

    @Autowired
    private TripRepository tripRepository;

    public List<TripModel> findAll() {
        return tripRepository.findAll();
    }

    public Optional<TripModel> findById(Long id) {
        return tripRepository.findById(id);
    }

    public TripModel save(TripModel tripModel) {
        return tripRepository.save(tripModel);
    }

    public void deleteById(Long id) {
        tripRepository.deleteById(id);
    }
}

