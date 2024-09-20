package com.gusttadev.relationshipccenter.domain.response;

import com.gusttadev.relationshipccenter.domain.enums.StatusRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestResponse {
    private Long id;
    private String subject;
    private StatusRequest statusRequest;
    private String attendantName;
}
