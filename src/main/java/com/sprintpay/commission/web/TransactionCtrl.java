/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.web;

import com.sprintpay.commission.dto.TransactionDTO;
import com.sprintpay.commission.entities.Transaction;
import com.sprintpay.commission.service.ICommissionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public @ResponseBody Transaction saveTransaction(@RequestBody TransactionDTO transaction){
        return commissionService.saveOrUpdateTransaction(transaction);
    }
    
    @PutMapping("/api/updateTransaction")
    public @ResponseBody Transaction updateTransaction(@RequestBody TransactionDTO transaction){
        return commissionService.saveOrUpdateTransaction(transaction);
    }
    
    @GetMapping("/api/findTransaction/{serviceId}")
    public @ResponseBody List<Transaction> findTransactionByService(@PathVariable int serviceId){
        return commissionService.findTransactionByServiceId(serviceId);
    }
    
    @GetMapping("/api/findAllTransaction")
    public @ResponseBody List<Transaction> findAllTransaction(){
        return commissionService.findAllTransaction();
    }
    
    @DeleteMapping("/api/deleteTransaction/{transactionId}")
    public int deleteTransaction(@PathVariable int transactionId){
        commissionService.deleteTransaction(transactionId);
        return transactionId;
    }
}
