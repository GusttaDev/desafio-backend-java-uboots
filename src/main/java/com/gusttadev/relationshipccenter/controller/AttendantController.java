package com.gusttadev.relationshipccenter.controller;

import com.gusttadev.relationshipccenter.domain.request.AttendantRequest;
import com.gusttadev.relationshipccenter.domain.response.AttendantResponse;
import com.gusttadev.relationshipccenter.service.AttendantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendant")
@RequiredArgsConstructor
public class AttendantController {

    private final AttendantService attendantService;
    @PostMapping
    public ResponseEntity<Void> createAttendants(@RequestBody List<AttendantRequest> attendantRequests) {
        attendantService.createAttendants(attendantRequests);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/request-count")
    public ResponseEntity<List<AttendantResponse>> getAttendantsWithRequestCount() {
        return ResponseEntity.ok(attendantService.getAttendantsWithRequestCount());
    }
}
