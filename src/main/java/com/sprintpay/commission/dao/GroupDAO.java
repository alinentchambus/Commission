/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.dao;

import com.sprintpay.commission.entities.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ALINE-PSE
 */
public interface GroupDAO extends JpaRepository<Groupe, Integer>{
    @Query("select g from Groupe g, Country c where c.group.id = g.id and c.code = :countryCode")
    Groupe findByCountryCode(@Param("countryCode")String countryCode);
    
    Groupe findByName(String name);
    
    Groupe findByNameAndIdNot(String name, int id);
}
