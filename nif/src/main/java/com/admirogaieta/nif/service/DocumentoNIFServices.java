package com.admirogaieta.nif.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.admirogaieta.nif.entity.DocumentoNIF;
import com.admirogaieta.nif.repository.DocumentoNIFRepository;

@Service
public class DocumentoNIFServices {

    @Autowired
    private DocumentoNIFRepository documentoNIFRepository;
       @Autowired
    private RestTemplate restTemplate;
    @Value("${bi.service.url}")
    private String biServiceUrl;
    
    public ResponseEntity<?> savedocumentoNIFEntity(DocumentoNIF documentoNIF) {
        try {
            // Verificar se o BI existe
            String biUrl = biServiceUrl  + documentoNIF.getNumbernif(); // Ajuste o parâmetro conforme necessário

            // Tenta obter o BI
            try {
                ResponseEntity<String> biResponse = restTemplate.getForEntity(biUrl, String.class);
                String responseBody = biResponse.getBody();
                if (responseBody.length()==401) {
                    // Se o BI existe, tentar salvar o NIF
                    DocumentoNIF documentoNIFEntity = documentoNIFRepository.save(documentoNIF);

                    if (documentoNIFEntity != null) {
                        return ResponseEntity.ok(documentoNIFEntity);
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error when creating NIF, please verify what's happening!");
                    }
                } else {
                    // Se o status não for OK, significa que o BI não foi encontrado
                    return ResponseEntity.ok().body(biResponse.status(0));
                }
            } catch (HttpClientErrorException e) {
                // Captura erros específicos e verifica se é 404
                if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BI NOT FOUND");
                } else {
                    // Para outros códigos de erro, re-throw a exceção
                    throw e;
                }
            }
        } catch (Exception e) {
            // Log o erro para depuração
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + e.getMessage());
        }
    }
    public ResponseEntity<?> findDocumentNIF(String numbernif)
    {
        DocumentoNIF documentoNIF= documentoNIFRepository.findByNumbernif(numbernif);
        try {
            if(documentoNIF !=null)
            {
                return ResponseEntity.ok(documentoNIF);
            }else return ResponseEntity.status(404).body("Not Found Nif");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error in server!");
        }
    }

}
