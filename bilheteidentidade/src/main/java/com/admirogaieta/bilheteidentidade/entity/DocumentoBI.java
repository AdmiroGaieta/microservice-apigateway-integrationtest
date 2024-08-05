package com.admirogaieta.bilheteidentidade.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class DocumentoBI {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String fullName;
    @Column(unique = true)
    private String identityCard;
    private String parentage;
    private String residence;
    private String placeOfBirth;
    private String gender;
    private String maritalStatus;
    private String validity;
    private String issuedDate;
    private String height;
    private String email;
}
