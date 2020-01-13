/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.dao;

import com.sprintpay.commission.entities.Groupe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ALINE-PSE
 */
public interface GroupDAO extends JpaRepository<Groupe, Integer>{
    @Query("select g from Groupe g, Country c where c.group.id = g.id and c.code = :countryCode and g.isActive = true")
    Groupe findByCountryCodeAndIsActiveTrue(@Param("countryCode")String countryCode);
    
    Groupe findByNameAndIsActiveTrue(String name);
    
    Groupe findByNameAndIdNotAndIsActiveTrue(String name, int id);
    
    @Query("select g from Groupe g where g.isActive = true ")
    List<Groupe> findAllAndIsActiveTrue();
    
    Groupe findByIdAndIsActiveTrue(int id);
}
