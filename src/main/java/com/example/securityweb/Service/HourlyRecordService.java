package com.example.securityweb.Service;


import org.springframework.stereotype.Service;

import com.example.securityweb.Entity.HourlyRecord;
import com.example.securityweb.Repository.HourlyRecordRepository;

import java.util.List;

@Service
public class HourlyRecordService {
    private final HourlyRecordRepository repository;

   
    public HourlyRecordService(HourlyRecordRepository repository) {
        this.repository = repository;
    }

    public void saveHourlyRecords(List<HourlyRecord> records) {
        repository.saveAll(records);
    }
}

