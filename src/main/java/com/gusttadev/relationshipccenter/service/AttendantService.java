package com.gusttadev.relationshipccenter.service;

import com.gusttadev.relationshipccenter.domain.entity.Attendant;
import com.gusttadev.relationshipccenter.domain.entity.TeamOperation;
import com.gusttadev.relationshipccenter.domain.enums.OperationType;
import com.gusttadev.relationshipccenter.domain.enums.StatusRequest;
import com.gusttadev.relationshipccenter.exception.BusinessException;
import com.gusttadev.relationshipccenter.repository.AttendantRepository;
import com.gusttadev.relationshipccenter.domain.request.AttendantRequest;
import com.gusttadev.relationshipccenter.domain.request.OperationRequest;
import com.gusttadev.relationshipccenter.domain.response.AttendantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AttendantService {

    private final AttendantRepository attendantRepository;
    private final TeamOperationService teamOperationService;
    private final RequestService requestService;

    public void createAttendants(List<AttendantRequest> attendantRequests) {

        List<Attendant> attendants = attendantRequests.stream().map(request -> {
            Attendant attendant = new Attendant();
            attendant.setName(request.getName());
            attendant.setTeamOperation(getTeamOperationFromRequest(request.getOperationRequest()));
            return attendant;
        }).toList();

        attendantRepository.saveAll(attendants);
    }

    private TeamOperation getTeamOperationFromRequest(OperationRequest operationRequest) {
        return teamOperationService.findTeamOperationByOperationType(operationRequest.getOperationType());
    }

    public List<Attendant> findAttendantByOperationType(OperationType operationType){
        List<Attendant> attendants = attendantRepository.findByTeamOperation_OperationType(operationType);

        if(CollectionUtils.isEmpty(attendants)){
            throw new BusinessException(HttpStatus.NOT_FOUND, "Nenhum atendente encontrado para o tipo"+"["+operationType+"] informado");
        }

        return attendants;
    }

    public List<AttendantResponse> getAttendantsWithRequestCount() {
        List<Attendant> attendants = attendantRepository.findAll();

        return attendants.stream().map(attendant -> {
            int count = requestService.countByAttendantAndStatusRequest(attendant, StatusRequest.IN_PROGRESS);
            return buildAttendantResponse(attendant.getName(), attendant.getTeamOperation().getOperationType(), count);
        }).toList();
    }

    private AttendantResponse buildAttendantResponse(String name, OperationType operationType, int count){
        return AttendantResponse.builder()
                .name(name)
                .operationType(operationType)
                .quantityRequestInProgress(count)
                .build();
    }

}
