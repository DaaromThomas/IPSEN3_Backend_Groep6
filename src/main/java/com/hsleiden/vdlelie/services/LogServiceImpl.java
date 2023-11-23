package com.hsleiden.vdlelie.services;

import com.hsleiden.vdlelie.dao.LogRepository;
import com.hsleiden.vdlelie.model.Log;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class LogServiceImpl implements LogService
{
    private final LogRepository logRepository;

    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public Optional<Log> findById(String id) {
        return logRepository.findById(id);
    }

    @Override
    public Log save(Log log) {
        log.setTime(LocalTime.now());
        return logRepository.save(log);
    }

    @Override
    public List<Log> findAll() {
        return (List<Log>) logRepository.findAll();
    }

    @Override
    public void delete(Log log) {
        logRepository.delete(log);
    }
}
