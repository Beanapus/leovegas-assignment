package com.leovegas.assignment.resource;

import com.leovegas.assignment.model.TransactionPayload;
import com.leovegas.assignment.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service){
        this.service = service;
    }

    @PostMapping(path = "api/debit")
    public ResponseEntity<String> debitAccount(@RequestBody TransactionPayload payload) throws Exception{
        return service.debitAccount(payload);
    }

    @PostMapping(path = "api/credit")
    public ResponseEntity<String> creditAccount(@RequestBody TransactionPayload payload){
        return ResponseEntity.ok("");
    }

}
