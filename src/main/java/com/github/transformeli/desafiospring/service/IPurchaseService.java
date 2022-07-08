package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.model.PurchaseRequest;
import com.github.transformeli.desafiospring.model.Ticket;
import java.util.List;

public interface IPurchaseService {
    public Ticket processPurchase(List<PurchaseRequest> purchaseList);
}
