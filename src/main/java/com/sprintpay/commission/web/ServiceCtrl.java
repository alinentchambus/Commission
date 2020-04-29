
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.web;

import com.sprintpay.commission.entities.Service;
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
public class ServiceCtrl {
    @Autowired
    ICommissionService commissionService;

    @GetMapping("/api/getAllServices")
    public List<Service> findAllServices() {
        return commissionService.findAllService();
    }
    
//    @PostMapping("/api/createService")
//    public ResponseEntity<Void> saveService(@RequestBody Service service){
//    
//        Service serviceAdded = commissionService.saveOrUpdateService(service);
//        
//        if(serviceAdded == null){
//            return ResponseEntity.noContent().build();
//        }
//        
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(serviceAdded.getId())
//                .toUri();
//        return ResponseEntity.created(location).build();
//    }
    
    @PostMapping("/api/createService")
    public @ResponseBody Service saveService(@RequestBody Service service){
        return commissionService.saveOrUpdateService(service);
    }
    
    @PutMapping("/api/updateService")
    public @ResponseBody Service updateService(@RequestBody Service service){
        return commissionService.saveOrUpdateService(service);
    }

    @DeleteMapping("/api/deleteService/{serviceId}")
    public int deleteService(@PathVariable int serviceId) {
         commissionService.deleteService(serviceId);
         return serviceId;
    }
}
