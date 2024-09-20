package com.gusttadev.relationshipccenter.service;

import com.gusttadev.relationshipccenter.domain.entity.Attendant;
import com.gusttadev.relationshipccenter.domain.entity.Request;
import com.gusttadev.relationshipccenter.domain.enums.StatusRequest;
import com.gusttadev.relationshipccenter.domain.response.RequestResponse;
import com.gusttadev.relationshipccenter.exception.BusinessException;
import com.gusttadev.relationshipccenter.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class RequestService {

    private final RequestRepository requestRepository;

    public List<Request> findByAttendantIdAndStatusRequest(Long attendantId, StatusRequest statusRequest){
        return requestRepository.findByAttendantIdAndStatusRequest(attendantId, statusRequest);
    }

    public void finalizeRequest(Long requestId){
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Solicitação não encontrada para o id ["+requestId+"]"));

        request.setStatusRequest(StatusRequest.COMPLETED);
        request.setUpdateAt(LocalDateTime.now());
        requestRepository.save(request);
    }

    public int countByAttendantAndStatusRequest(Attendant attendant, StatusRequest statusRequest){
        return requestRepository.countByAttendantAndStatusRequest(attendant, statusRequest);
    }


    public Page<RequestResponse> getRequestsByStatus(StatusRequest statusRequest, Pageable pageable) {
        Page<Request> requests = requestRepository.findByStatusRequest(statusRequest, pageable);
        List<RequestResponse> requestResponses = requests.stream().map(this::buildRequestResponse).toList();
        return new PageImpl<>(requestResponses, pageable, requests.getTotalElements());
    }

    public List<Request> findOpenRequests(){
        return requestRepository.findByStatusRequest(StatusRequest.OPEN);
    }

    private RequestResponse buildRequestResponse(Request request){
        return RequestResponse.builder()
                .id(request.getId())
                .subject(request.getSubject())
                .statusRequest(request.getStatusRequest())
                .attendantName(Objects.nonNull(request.getAttendant()) ? request.getAttendant().getName() : "")
                .build();
    }
}
