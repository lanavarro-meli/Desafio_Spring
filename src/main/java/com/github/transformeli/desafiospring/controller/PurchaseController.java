package com.github.transformeli.desafiospring.controller;

import com.github.transformeli.desafiospring.model.PurchaseRequest;
import com.github.transformeli.desafiospring.model.Ticket;
import com.github.transformeli.desafiospring.service.PurchaseService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PurchaseController {

    @Autowired
    private PurchaseService service;

    @PostMapping("/purchase-request")
    public ResponseEntity<Ticket> processPurchase(@RequestBody List<PurchaseRequest> purchaseList)
    {
        Ticket ticket = service.processPurchase(purchaseList);
        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
    }

}
