package com.hsleiden.vdlelie.controllers;

import com.hsleiden.vdlelie.model.Location;
import com.hsleiden.vdlelie.model.Stock;
import com.hsleiden.vdlelie.services.LocationService;
import com.hsleiden.vdlelie.services.StockService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LocationController
{

    private final StockService stockService;
    private final LocationService locationService;

    public LocationController(LocationService locationService, StockService stockService) {
        this.locationService = locationService;
        this.stockService = stockService;
    }

    @PostMapping("/locations")
    @PreAuthorize("hasRole('ADMIN')")
    public Location saveLocation(@RequestParam String address){
        Stock stock = new Stock();
        Location location = new Location(address, stock);
        stockService.save(stock);
        return locationService.save(location);
    }

    @GetMapping("/locations")
    public List<Location> getAllLocations(){
        return locationService.findAll();
    }

    @GetMapping("/locations/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Optional<Location> getLocationById(@PathVariable String id){
        return locationService.findById(id);
    }

    @DeleteMapping("/locations/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteLocation(@PathVariable String id){
        if (locationService.findById(id).isPresent()){
            Location location = locationService.findById(id).get();
            locationService.delete(location);
        }
        else {
            return;
        }
    }

}
