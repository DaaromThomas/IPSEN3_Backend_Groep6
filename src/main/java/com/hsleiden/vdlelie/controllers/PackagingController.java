package com.hsleiden.vdlelie.controllers;

import com.hsleiden.vdlelie.dao.PackagingRepository;
import com.hsleiden.vdlelie.dto.PackageChangeRequest;
import com.hsleiden.vdlelie.model.Customer;
import com.hsleiden.vdlelie.model.Packaging;
import com.hsleiden.vdlelie.model.Stock;
import com.hsleiden.vdlelie.services.PackagingService;
import com.hsleiden.vdlelie.services.StockService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class PackagingController
{
    private final PackagingService packagingService;
    private final StockService stockService;

    private final PackagingRepository packagingRepository;

    public PackagingController(PackagingService packagingService, StockService stockService, PackagingRepository packagingRepository) {
        this.packagingService = packagingService;
        this.stockService = stockService;
        this.packagingRepository = packagingRepository;
    }

    @PostMapping("/packages")
    @PreAuthorize("hasRole('ADMIN')")
    public Packaging savePackage(@RequestParam String stockId, @RequestParam String name, @RequestParam String packagingGroup, @RequestParam int amount, @RequestParam int minAmount){
        Stock stock = null;
        if (stockService.findById(stockId).isPresent()){
            stock = stockService.findById(stockId).get();
        }
        else {
            return null;
        }

        Packaging _package = new Packaging(stock, amount, minAmount, name, packagingGroup);
        return packagingService.save(_package);
    }

    @GetMapping("/packages")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Packaging> getAllPackages(){
        return packagingService.findAll();
    }

    @GetMapping("/packages/name")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Packaging getPackageByName(@RequestParam String name) {
        if (packagingRepository.findByName(name).isPresent()) {
            return packagingRepository.findByName(name).get();
        } else {
            return null;
        }
    }

    @GetMapping("/packages/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Optional<Packaging> getPackageById(@PathVariable String id){
        return packagingService.findById(id);
    }

    @DeleteMapping("/packages/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePackage(@PathVariable String id){
        if (packagingService.findById(id).isPresent())
        {
            Packaging _package =  packagingService.findById(id).get();
            packagingService.delete(_package);
        }
        else {
            return;
        }
    }

    @PatchMapping("/packages/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void updatePackage(@PathVariable String id, @RequestParam(required = false) String name, @RequestParam(required = false) String group, @RequestParam(required = false) Integer amount, @RequestParam(required = false) Integer minAmount){
        Optional<Packaging> possiblePackaging = packagingService.findById(id);

        if (possiblePackaging.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Packaging not found");
        }

        Packaging packaging = possiblePackaging.get();

        if (name != null){
            packaging.setName(name);
        }

        if (group != null){
            packaging.setPackagingGroup(group);
        }


        if (amount != null){
            packaging.setAmountinstock(amount);
        }

        if (minAmount != null){
            packaging.setMinAmount(minAmount);
        }

        packagingService.save(packaging);
    }
    @PostMapping("/packages/update")
    public void updatePackage(@RequestBody @Valid PackageChangeRequest packageChangeRequest){
        this.packagingService.updatePackageDetails(packageChangeRequest);
    }


}
