package com.admirogaieta.bilheteidentidade.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admirogaieta.bilheteidentidade.entity.DocumentoBI;

@Repository
public interface DocumentoBIRepository  extends JpaRepository <DocumentoBI,UUID>{
    DocumentoBI findByIdentityCard(String identityCard);

}
