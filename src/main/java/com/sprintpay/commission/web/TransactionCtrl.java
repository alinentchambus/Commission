/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.web;

import com.sprintpay.commission.dto.TransactionDTO;
import com.sprintpay.commission.entities.Transaction;
import com.sprintpay.commission.service.ICommissionService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author ALINE-PSE
 */
@RestController
public class TransactionCtrl {
    @Autowired
    ICommissionService commissionService;
   
//    @PostMapping("/api/createTransaction")
//    public ResponseEntity<Void> saveService(@RequestBody TransactionDTO transaction){
//    
//        Transaction transactionAdded = commissionService.saveOrUpdateTransaction(transaction);
//        
//        if(transactionAdded == null){
//            return ResponseEntity.noContent().build();
//        }
//        
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(transactionAdded.getId())
//                .toUri();
//        return ResponseEntity.created(location).build();
//    }
    
    @PostMapping("/api/createTransaction")
    public @ResponseBody Transaction saveService(@RequestBody TransactionDTO transaction){
        return commissionService.saveOrUpdateTransaction(transaction);
    }
    
    @PutMapping("/api/updateTransaction")
    public @ResponseBody Transaction updateService(@RequestBody TransactionDTO transaction){
        return commissionService.saveOrUpdateTransaction(transaction);
    }
    
    @DeleteMapping("/api/deleteTransaction/{transactionId}")
    public void deleteTransaction(@PathVariable int transactionId){
        commissionService.deleteTransaction(transactionId);
    }
}
