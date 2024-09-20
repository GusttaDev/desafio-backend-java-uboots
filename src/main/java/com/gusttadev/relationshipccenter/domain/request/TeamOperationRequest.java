package com.gusttadev.relationshipccenter.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamOperationRequest {
     private List<OperationRequest> operationTypes;
}
