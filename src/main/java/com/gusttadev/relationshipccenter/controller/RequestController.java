package com.gusttadev.relationshipccenter.controller;

import com.gusttadev.relationshipccenter.domain.entity.Request;
import com.gusttadev.relationshipccenter.domain.enums.StatusRequest;
import com.gusttadev.relationshipccenter.domain.request.PayloadRequest;
import com.gusttadev.relationshipccenter.domain.response.RequestResponse;
import com.gusttadev.relationshipccenter.service.DistributionService;
import com.gusttadev.relationshipccenter.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/request")
public class RequestController {

    private final RequestService requestService;
    private final DistributionService service;

    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody PayloadRequest payloadRequest) {
        return new ResponseEntity<>(service.create(payloadRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{requestId}/finalize")
    public ResponseEntity<Void> finalizeRequest(@PathVariable Long requestId) {
        requestService.finalizeRequest(requestId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<RequestResponse>> getRequestsByStatus(
            @RequestParam StatusRequest statusRequest,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(requestService.getRequestsByStatus(statusRequest, pageRequest));
    }
}
