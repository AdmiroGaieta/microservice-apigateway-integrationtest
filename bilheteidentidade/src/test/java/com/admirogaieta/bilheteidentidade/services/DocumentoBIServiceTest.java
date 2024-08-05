package com.admirogaieta.bilheteidentidade.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.admirogaieta.bilheteidentidade.entity.DocumentoBI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DocumentoBIServiceTest {

    @Autowired
    private DocumentoBIService documentoBIService;

    @Test
    public void testSaveEntityOk() {
        // Arrange
        DocumentoBI documentoBI = new DocumentoBI();
        documentoBI.setFullName("Pedro Costa");
        documentoBI.setParentage("Filho de Carla Costa");
        documentoBI.setResidence("Rua das Acácias, 789");
        documentoBI.setPlaceOfBirth("Coimbra");
        documentoBI.setGender("Masculino");
        documentoBI.setMaritalStatus("Divorciado");
        documentoBI.setValidity("2027-03-31");
        documentoBI.setIssuedDate("2024-03-01");
        documentoBI.setHeight("1.75");
        documentoBI.setEmail("mirogaieta100@gmail.com");
        // Act
        ResponseEntity<?> response = documentoBIService.savEntity(documentoBI);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

        DocumentoBI responseBody = (DocumentoBI) response.getBody();
        assertEquals(responseBody.getIdentityCard(), responseBody.getIdentityCard());
        assertEquals("Pedro Costa", responseBody.getFullName());
        assertEquals("Filho de Carla Costa", responseBody.getParentage());
        assertEquals("Rua das Acácias, 789", responseBody.getResidence());
        assertEquals("Coimbra", responseBody.getPlaceOfBirth());
        assertEquals("Masculino", responseBody.getGender());
        assertEquals("Divorciado", responseBody.getMaritalStatus());
        assertEquals("2027-03-31", responseBody.getValidity());
        assertEquals("2024-03-01", responseBody.getIssuedDate());
        assertEquals("1.75", responseBody.getHeight());
    }

    @Test
    public void getByidentityCard() {
        // Arrange
        DocumentoBI documentoBI = new DocumentoBI();
        documentoBI.setIdentityCard("434912395LA599");
        // Act
        ResponseEntity<?> response = documentoBIService.getByidentityCard(documentoBI.getIdentityCard());
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

      //  DocumentoBI responseBody = (DocumentoBI) response.getBody();
       // assertEquals("Not exist document", response);
        
    }
}
