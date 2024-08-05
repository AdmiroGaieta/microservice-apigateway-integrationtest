package com.admirogaieta.nif.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admirogaieta.nif.entity.DocumentoNIF;

@Repository
public interface DocumentoNIFRepository extends JpaRepository<DocumentoNIF,UUID>{
    DocumentoNIF findByNumbernif(String Numbernif);
}
