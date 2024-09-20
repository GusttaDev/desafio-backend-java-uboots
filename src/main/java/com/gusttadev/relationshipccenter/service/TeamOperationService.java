package com.gusttadev.relationshipccenter.service;

import com.gusttadev.relationshipccenter.domain.entity.TeamOperation;
import com.gusttadev.relationshipccenter.domain.enums.OperationType;
import com.gusttadev.relationshipccenter.exception.BusinessException;
import com.gusttadev.relationshipccenter.repository.TeamOperationRepository;
import com.gusttadev.relationshipccenter.domain.request.OperationRequest;
import com.gusttadev.relationshipccenter.domain.request.TeamOperationRequest;
import com.gusttadev.relationshipccenter.domain.response.TeamOperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamOperationService {
    
    private final TeamOperationRepository teamOperationRepository;

    public TeamOperation findTeamOperationByOperationType(OperationType operationType){
        return teamOperationRepository.findByOperationType(operationType).orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Operação ["+operationType+"] não localizada"));
    }

    public List<TeamOperationResponse> createTeamOperation(TeamOperationRequest teamOperationRequest) {
        List<TeamOperation> teamOperations = teamOperationRequest.getOperationTypes().stream().map(this::buildTeamOperation).toList();
        List<TeamOperation> teamOperationList = teamOperationRepository.saveAll(teamOperations);

        return teamOperationList.stream().map(this::buildTeamOperationResponse).toList();
    }

    private TeamOperation buildTeamOperation(OperationRequest operationRequest){
        return TeamOperation.builder().operationType(operationRequest.getOperationType()).build();
    }

    private TeamOperationResponse buildTeamOperationResponse(TeamOperation teamOperation){
        return TeamOperationResponse.builder()
                .id(teamOperation.getId())
                .operationType(teamOperation.getOperationType())
                .build();
    }


}