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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @GetMapping("/api/findCommission/{transactionCode}/{srcCountryCode}/{destCountryCode}/{amount}")
    public DoubleResult findCommission(@PathVariable String transactionCode, @PathVariable String srcCountryCode, 
            @PathVariable String destCountryCode, @PathVariable double amount) {
       
        double result = commissionService.findCommission(srcCountryCode,destCountryCode,transactionCode,amount);
        
        return new DoubleResult(result);
    }
    

//    @PostMapping("/api/configCommission")
//    public ResponseEntity<Void> saveService(@RequestBody CommissionDTO commissionDTO) {
//
//        Commission commissionAdded = commissionService.saveOrUpdateCommission(commissionDTO);
//
//        if (commissionAdded == null) {
//            return ResponseEntity.noContent().build();
//        }
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(commissionAdded.getId())
//                .toUri();
//        return ResponseEntity.created(location).build();
//    }
    
    @PostMapping("/api/configCommission")
    public @ResponseBody Commission saveService(@RequestBody CommissionDTO commissionDTO) {
        return commissionService.saveOrUpdateCommission(commissionDTO);
    }
}
