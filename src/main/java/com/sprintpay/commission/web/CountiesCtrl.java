/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.web;

import com.sprintpay.commission.dto.CountryDTO;
import com.sprintpay.commission.entities.Country;
import com.sprintpay.commission.service.ICommissionService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author ALINE-PSE
 */
@RestController
public class CountiesCtrl {

    @Autowired
    ICommissionService commissionService;

    @GetMapping("/api/getCountries/{groupId}")
    public List<Country> getCountryByGroupId(@PathVariable int groupId) {
        return commissionService.findCountryByGroupId(groupId);
    }
    
    
    @PostMapping("/api/saveCountry")
    public ResponseEntity<Void> saveCountry(@RequestBody CountryDTO country){
    
        Country countryAdded = commissionService.saveOrUpdateCountry(country);
        if(countryAdded == null){
            return ResponseEntity.noContent().build();
        }
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(countryAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    
    @PutMapping("/api/updateCountry")
    public ResponseEntity<Void> updateCountry(@RequestBody CountryDTO country){
    
        Country countryAdded = commissionService.saveOrUpdateCountry(country);
        if(countryAdded == null){
            return ResponseEntity.noContent().build();
        }
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(countryAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    
    @DeleteMapping("/api/deleteCountry/{countryId}")
    public void deleteCountry(@PathVariable int countryId){
        commissionService.deleteCountry(countryId);
    }
}
