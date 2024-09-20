package com.gusttadev.relationshipccenter.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendantRequest {
    private String name;
    private OperationRequest operationRequest;
}
