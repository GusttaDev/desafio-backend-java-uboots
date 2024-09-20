package com.gusttadev.relationshipccenter.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gusttadev.relationshipccenter.domain.enums.StatusRequest;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    @Column(name = "status_request")
    @Enumerated(EnumType.STRING)
    private StatusRequest statusRequest;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "attendant_id")
    private Attendant attendant;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", statusRequest=" + statusRequest +
                '}';
    }
}