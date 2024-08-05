package com.admirogaieta.bilheteidentidade.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admirogaieta.bilheteidentidade.entity.DocumentoBI;
import com.admirogaieta.bilheteidentidade.services.DocumentoBIService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/bi")
public class DocumentoBIController {
    @Autowired
    private DocumentoBIService documentoBIService;

    @PostMapping
    public ResponseEntity<?> saveEntity(@RequestBody DocumentoBI entity)  
    {
        return documentoBIService.savEntity(entity);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getByidentityCard(@PathVariable String id)  
    {
        var response = documentoBIService.getByidentityCard(id);
        if(response!=null)
        {
            return ResponseEntity.ok(response);
        }else return ResponseEntity.badRequest().body("error");
        //return documentoBIService.getByidentityCard(id);
    }
    
    

}
