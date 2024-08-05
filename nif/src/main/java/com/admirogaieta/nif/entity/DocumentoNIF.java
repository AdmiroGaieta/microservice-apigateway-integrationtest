package com.admirogaieta.nif.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class DocumentoNIF {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID Id ;
    private String fullname;
    @Column(unique = true)
    private String numbernif;
    private String gender;
    private String placeOfBirth;
}
