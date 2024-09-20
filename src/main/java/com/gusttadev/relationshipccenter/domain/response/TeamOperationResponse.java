package com.gusttadev.relationshipccenter.domain.response;

import com.gusttadev.relationshipccenter.domain.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamOperationResponse {
    private Long id;
    private OperationType operationType;
}
