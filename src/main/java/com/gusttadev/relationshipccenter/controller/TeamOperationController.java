package com.gusttadev.relationshipccenter.controller;

import com.gusttadev.relationshipccenter.domain.request.TeamOperationRequest;
import com.gusttadev.relationshipccenter.domain.response.TeamOperationResponse;
import com.gusttadev.relationshipccenter.service.TeamOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/team-operations")
@RequiredArgsConstructor
public class TeamOperationController {

    private final TeamOperationService teamOperationService;

    @PostMapping
    public ResponseEntity<List<TeamOperationResponse>> createTeamOperation(@RequestBody TeamOperationRequest teamOperationRequest) {
        return new ResponseEntity<>(teamOperationService.createTeamOperation(teamOperationRequest), HttpStatus.CREATED);
    }
}
