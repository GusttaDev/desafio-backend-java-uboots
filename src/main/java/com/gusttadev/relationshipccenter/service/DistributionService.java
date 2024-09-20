package com.gusttadev.relationshipccenter.service;

import com.gusttadev.relationshipccenter.domain.entity.Attendant;
import com.gusttadev.relationshipccenter.domain.entity.Request;
import com.gusttadev.relationshipccenter.domain.enums.OperationType;
import com.gusttadev.relationshipccenter.domain.enums.StatusRequest;
import com.gusttadev.relationshipccenter.domain.request.PayloadRequest;
import com.gusttadev.relationshipccenter.exception.BusinessException;
import com.gusttadev.relationshipccenter.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DistributionService {

    public static final String NO_ATTENDANTS_AVAILABLE_AT_THE_MOMENT = "Sem atendentes disponíveis no momento.";

    private final AttendantService attendantService;
    private final RequestService requestService;
    private final RequestRepository requestRepository;

    public Request create(PayloadRequest payloadRequest){
        Request request = new Request();
                request.setSubject(payloadRequest.getSubject());
                request.setStatusRequest(StatusRequest.OPEN);
                requestRepository.save(request);
        return this.distribution(request);
            
    }
    public Request distribution(Request request){
            OperationType type = getTypeAttendant(request.getSubject());

            List<Attendant> attendants = attendantService.findAttendantByOperationType(type);

            Attendant availableAttendant = null;

            for (Attendant attendant : attendants) {
                List<Request> requestsInService = requestService.findByAttendantIdAndStatusRequest(attendant.getId(), StatusRequest.IN_PROGRESS);
                if (requestsInService.size() < 3) {
                    availableAttendant = attendant;
                    break;
                }
            }

            if (availableAttendant != null) {
                request.setStatusRequest(StatusRequest.IN_PROGRESS);
                request.setAttendant(availableAttendant);
                return requestRepository.save(request);

            } else {
                throw new BusinessException(HttpStatus.CONFLICT, NO_ATTENDANTS_AVAILABLE_AT_THE_MOMENT);
            }
    }

    private OperationType getTypeAttendant(String subject) {
        String subjectLowerCase = subject.toLowerCase();
        return switch (subjectLowerCase) {
            case "problemas com cartão" -> OperationType.CARD;
            case "contratação de empréstimo" -> OperationType.LOANS;
            case "outros assuntos" -> OperationType.OTHER_MATTERS;
            default -> throw new BusinessException(HttpStatus.BAD_REQUEST,
                    String.format("Assunto [%s] não reconhecido. Opções disponíveis: %s.",
                    subject, getAvailableOperationTypes()));
        };
    }

    private String getAvailableOperationTypes() {
        return String.join(", ",
                "Problemas com cartão",
                "Contratação de empréstimo",
                "Outros assuntos");
    }
}
