package com.example.travel.service;

import com.example.travel.dao.TripDao;
import com.example.travel.model.TripModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripDao tripDao;

    @Cacheable(value = "trips")
    public List<TripModel> getAllTrips() {
        return tripDao.findAll();
    }

    @Cacheable(value = "trips", key = "#id")
    public TripModel getTripById(Long id) {
        return tripDao.findById(id).orElse(null);
    }

    @CacheEvict(value = "trips", allEntries = true)
    public TripModel saveTrip(TripModel tripModel) {
        return tripDao.save(tripModel);
    }

    @CacheEvict(value = "trips", allEntries = true)
    public void deleteTrip(Long id) {
        tripDao.deleteById(id);
    }
}

