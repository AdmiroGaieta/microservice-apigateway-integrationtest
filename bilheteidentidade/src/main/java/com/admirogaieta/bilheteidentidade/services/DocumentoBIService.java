package com.admirogaieta.bilheteidentidade.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.admirogaieta.bilheteidentidade.entity.DocumentoBI;
import com.admirogaieta.bilheteidentidade.repository.DocumentoBIRepository;

import lombok.experimental.var;

@Service
public class DocumentoBIService {

    @Autowired
    public DocumentoBIRepository documentoBIRepository;
    String identityCard;
    public ResponseEntity<?>  savEntity(DocumentoBI DocumentoBI)
    {
         try {
                identityCard = generateUniqueIdentityCard();
                DocumentoBI.setIdentityCard(identityCard);
                var response = documentoBIRepository.save(DocumentoBI);
                if (response!=null) {
                    return ResponseEntity.ok(response);
                } else  return ResponseEntity.ok("User existente ou não segue os parâmetros de construção");
             } catch (Exception e) {
                return ResponseEntity.badRequest().body("Erro no server!");
             }
    }


    public ResponseEntity<?> getByEntity(UUID id)
    {
          try {
            var response=documentoBIRepository.findById(id);
           if (response.isPresent()) 
            {
                return ResponseEntity.ok(response.get());
            }else return ResponseEntity.ok().body("Not exist document");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Trouble with server!");
            }
    }
    
    public ResponseEntity<?> getByidentityCard(String identityCard)
    {
          try {
            var response=documentoBIRepository.findByIdentityCard(identityCard);
           if (response != null) 
            {
                return ResponseEntity.ok(response);
            }else return ResponseEntity.status(404).body("Not exist document");
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Trouble with server!");
            }
    }




    // - Funções para gerar números do BI de modo automático
    private String generateUniqueIdentityCard() {
        String numberPart = generateRandomNumberPart();
        return numberPart + "LA" + generateRandomSuffix();
    }

    private String generateRandomNumberPart() {
        // Gerar os primeiros números do BI
        return String.format("%09d", (int) (Math.random() * 1_000_000_000));
    }

    private String generateRandomSuffix() {
        // Gerar os últimos 3 números do BI
        return String.format("%03d", (int) (Math.random() * 1_000));
    }

}
