package com.hsleiden.vdlelie.services;

import com.hsleiden.vdlelie.dao.PackagingRepository;
import com.hsleiden.vdlelie.model.Packaging;
import org.springframework.stereotype.Service;

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
}
