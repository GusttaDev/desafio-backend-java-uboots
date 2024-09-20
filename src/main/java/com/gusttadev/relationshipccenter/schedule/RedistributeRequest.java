package com.gusttadev.relationshipccenter.schedule;

import com.gusttadev.relationshipccenter.domain.entity.Request;
import com.gusttadev.relationshipccenter.service.DistributionService;
import com.gusttadev.relationshipccenter.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class RedistributeRequest {

    private final RequestService requestService;
    private final DistributionService distributionService;
    @Scheduled(cron = "${redistribution.schedule.time}")
    public void redistributeOpenRequest(){
        log.info("Inicio da redistribuição de solicitações abertas.");
        List<Request> openRequests = requestService.findOpenRequests();
        log.info("Preparando um total de {} solicitações para redistribuir", openRequests.size());
        if(!CollectionUtils.isEmpty(openRequests)){
            for (Request openRequest : openRequests) {
                log.info("Redistribuindo a solicitação de ID = {}", openRequest.getId());
                distributionService.distribution(openRequest);
            }
        }
        log.info("Fim da redistribuição de solicitações abertas.");
    }

}
