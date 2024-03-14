package com.jorder.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "favor_request", schema = "public")
public class FavorRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User applicant;
    @ManyToOne
    private User required;
    @ManyToOne
    private Schedule schedule;
    @Enumerated(EnumType.STRING)
    StatusFavor statusFavor;

    public void updateStatus(StatusFavor newStatus) throws Exception {
        switch (statusFavor) {
            case SOLICITADA:
                if (newStatus == StatusFavor.ACEITA || newStatus == StatusFavor.CANCELADA) {
                    this.statusFavor = newStatus;
                }
                throw new Exception("Status inválido!");

            case ACEITA:
                if (newStatus == StatusFavor.CANCELADA || newStatus == StatusFavor.CONCLUIDA) {
                    this.statusFavor = newStatus;
                }
                throw new Exception("Status inválido!");

            case CANCELADA:
                if (newStatus == StatusFavor.SOLICITADA) {
                    this.statusFavor = newStatus;
                }
                throw new Exception("Status inválido!");

            default:
                System.out.println("ERROR");
                throw new Exception("Status inválido!");
        }
    }

}
