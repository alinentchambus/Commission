/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.dao;

import com.sprintpay.commission.entities.Service;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author ALINE-PSE
 */
public interface ServiceDAO extends JpaRepository<Service, Integer >{
    Service findByNameAndIsActiveTrue(String name);
    Service findByNameAndIdNotAndIsActiveTrue(String name, int id);
    
    @Query("select s from Service s where s.isActive = true")
    List<Service> findAllAndIsActiveTrue();
    
    Service findByIdAndIsActiveTrue(int id);
}
