package com.gusttadev.relationshipccenter.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attendant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "team_operation_id", nullable = false)
    private TeamOperation teamOperation;

    @JsonManagedReference
    @OneToMany(mappedBy = "attendant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Request> requests;

    @Override
    public String toString() {
        return "Attendant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}