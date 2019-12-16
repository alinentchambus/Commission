/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.web;

import com.sprintpay.commission.entities.Groupe;
import com.sprintpay.commission.service.ICommissionService;
import java.net.URI;
import java.util.List;
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
public class GroupCtrl {
    @Autowired
    ICommissionService commissionService;
    
    @GetMapping("/groups")
    public List<Groupe> getAllGroups() {
        
        return commissionService.findAllGroup();
    }
    
    @PostMapping("/groups")
    public ResponseEntity<Void> SaveGroup(@RequestBody Groupe group){
        Groupe groupAdded = commissionService.saveOrUpdateGroup(group);
        
        if(groupAdded == null){
            return ResponseEntity.noContent().build();
        }
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(groupAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    
    
    
    
    
}
