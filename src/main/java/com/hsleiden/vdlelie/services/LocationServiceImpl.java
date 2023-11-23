package com.hsleiden.vdlelie.services;

import com.hsleiden.vdlelie.dao.LocationRepository;
import com.hsleiden.vdlelie.model.Location;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService{
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Optional<Location> findById(String id) {
        return locationRepository.findById(id);
    }

    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public List<Location> findAll() {
        return (List<Location>) locationRepository.findAll();
    }

    @Override
    public void delete(Location location) {
        locationRepository.delete(location);
    }
}
