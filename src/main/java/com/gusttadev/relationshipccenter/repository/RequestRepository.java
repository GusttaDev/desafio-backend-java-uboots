package com.gusttadev.relationshipccenter.repository;

import com.gusttadev.relationshipccenter.domain.entity.Attendant;
import com.gusttadev.relationshipccenter.domain.entity.Request;
import com.gusttadev.relationshipccenter.domain.enums.StatusRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByAttendantIdAndStatusRequest(Long attendantId, StatusRequest statusRequest);
    int countByAttendantAndStatusRequest(Attendant attendant, StatusRequest statusRequest);
    Page<Request> findByStatusRequest(StatusRequest statusRequest, Pageable pageable);
    List<Request> findByStatusRequest(StatusRequest statusRequest);
}
