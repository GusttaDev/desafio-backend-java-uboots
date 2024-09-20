package com.gusttadev.relationshipccenter.repository;

import com.gusttadev.relationshipccenter.domain.entity.Attendant;
import com.gusttadev.relationshipccenter.domain.enums.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendantRepository extends JpaRepository<Attendant, Long> {
    List<Attendant> findByTeamOperation_OperationType(OperationType type);
}

