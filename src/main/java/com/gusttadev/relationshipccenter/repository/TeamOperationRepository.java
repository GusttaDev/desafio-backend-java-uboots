package com.gusttadev.relationshipccenter.repository;

import com.gusttadev.relationshipccenter.domain.entity.TeamOperation;
import com.gusttadev.relationshipccenter.domain.enums.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamOperationRepository extends JpaRepository<TeamOperation, Long> {
    Optional<TeamOperation> findByOperationType(OperationType operationType);
}
