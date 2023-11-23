package com.hsleiden.vdlelie.services;


import com.hsleiden.vdlelie.model.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LogService
{
    Optional<Log> findById(String id);
    Log save(Log log);
    List<Log> findAll();
    void delete(Log log);
}
