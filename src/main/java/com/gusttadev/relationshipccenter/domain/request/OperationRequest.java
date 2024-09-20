package com.gusttadev.relationshipccenter.domain.request;

import com.gusttadev.relationshipccenter.domain.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationRequest {
    private OperationType operationType;
}
