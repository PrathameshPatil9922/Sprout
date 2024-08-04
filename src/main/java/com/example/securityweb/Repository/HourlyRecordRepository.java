package com.example.securityweb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.securityweb.Entity.HourlyRecord;

public interface HourlyRecordRepository extends JpaRepository<HourlyRecord, Long> {
}

