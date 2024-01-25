package com.hsleiden.vdlelie.services;

import com.hsleiden.vdlelie.dao.PackagingRepository;
import com.hsleiden.vdlelie.dto.PackageChangeRequest;
import com.hsleiden.vdlelie.model.Packaging;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PackagingServiceImpl implements PackagingService
{
    private final PackagingRepository packagingRepository;

    public PackagingServiceImpl(PackagingRepository packagingRepository) {
        this.packagingRepository = packagingRepository;
    }

    @Override
    public Optional<Packaging> findById(String id) {
        return packagingRepository.findById(id);
    }

    @Override
    public Packaging save(Packaging _package) {
        return packagingRepository.save(_package);
    }

    @Override
    public List<Packaging> findAll() {
        return (List<Packaging>) packagingRepository.findAll();
    }

    @Override
    public void delete(Packaging _package) {
        packagingRepository.delete(_package);
    }

    @Transactional
    public Packaging updatePackageDetails(PackageChangeRequest request) {
        Packaging productPackage = packagingRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + request.getId()));


        productPackage.setAmountinstock(request.getAmountInStock());
        productPackage.setMinAmount(request.getMinAmount());
        productPackage.setName(request.getName());
        return packagingRepository.save(productPackage);

    }

    @Override
    public List<Packaging> findByIsDeletedFalse() {
        return this.packagingRepository.findByisDeletedFalse();
    }
}
