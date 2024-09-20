package com.gusttadev.relationshipccenter.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gusttadev.relationshipccenter.domain.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "team_operation")
@Entity
public class TeamOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @JsonManagedReference
    @OneToMany(mappedBy = "teamOperation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendant> attendants;

    @Override
    public String toString() {
        return "TeamOperation{" +
                "id=" + id +
                ", operationType=" + operationType +
                '}';
    }
}
