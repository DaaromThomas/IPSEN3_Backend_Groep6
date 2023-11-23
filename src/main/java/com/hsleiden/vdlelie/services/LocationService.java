package com.hsleiden.vdlelie.services;

import com.hsleiden.vdlelie.model.Location;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LocationService
{
    Optional<Location> findById(String id);
    Location save(Location location);
    List<Location> findAll();
    void delete(Location location);

}
