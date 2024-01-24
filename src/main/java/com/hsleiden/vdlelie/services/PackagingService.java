package com.hsleiden.vdlelie.services;

import com.hsleiden.vdlelie.dto.PackageChangeRequest;
import com.hsleiden.vdlelie.model.Packaging;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PackagingService
{
    Optional<Packaging> findById(String id);
    Packaging save(Packaging _package);

    List<Packaging> findAll();

    void delete(Packaging _package);
    Packaging updatePackageDetails(PackageChangeRequest request);
}
