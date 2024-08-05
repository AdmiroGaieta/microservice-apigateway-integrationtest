package com.admirogaieta.nif.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admirogaieta.nif.entity.DocumentoNIF;
import com.admirogaieta.nif.service.DocumentoNIFServices;

@RestController
@RequestMapping("/api/nif")
public class DocumentoNIFController {

    @Autowired
    private DocumentoNIFServices documentoNIFService;

    @PostMapping
    public ResponseEntity<?> savEntity(@RequestBody DocumentoNIF documentoNIF) {

             var response= documentoNIFService.savedocumentoNIFEntity(documentoNIF);
          if (response!=null) {
               return ResponseEntity.ok().body(response);
          }
          else return ResponseEntity.ok("Error");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findEntity(@PathVariable String id) {
     var response = documentoNIFService.findDocumentNIF(id);
     return ResponseEntity.ok().body(response);
    }
}
