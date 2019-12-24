/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.web;

import com.sprintpay.commission.dto.CommissionDTO;
import com.sprintpay.commission.dto.DoubleResult;
import com.sprintpay.commission.entities.Commission;
import com.sprintpay.commission.service.ICommissionService;
import com.sprintpay.commission.web.parameters.CommissionParams;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author ALINE-PSE
 */
@RestController
public class CommissionCtrl {

    @Autowired
    ICommissionService commissionService;
    @GetMapping("/Commissions/find")
    public DoubleResult findCommission(CommissionParams params) {
       
        double result = commissionService.findCommission(params.getSrcCountryCode(), params.getDestCountryCode(),
                params.getTransactionCode(), params.getAmount());
        
        return new DoubleResult(result);
    }
    

    @PostMapping("/Commissions")
    public ResponseEntity<Void> saveService(@RequestBody CommissionDTO commissionDTO) {

        Commission commissionAdded = commissionService.saveOrUpdateCommission(commissionDTO);

        if (commissionAdded == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(commissionAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
