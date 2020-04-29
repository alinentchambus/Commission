/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.dao;

import com.sprintpay.commission.entities.Country;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ALINE-PSE
 */
@Repository
public interface CountryDAO extends JpaRepository<Country, Integer>{
    
    @Query("select c from Country c where c.group.id = :groupId and c.isActive = true")
    List<Country> findByGroupeIdAndIsActiveTrue(@Param("groupId") int groupId);
    
    @Query("select c from Country c where c.isActive = true ")
    List<Country> findAllAndIsActiveTrue();
    
    Country findByCodeAndIsActiveTrue( String code);
    
    Country findByNameAndIsActiveTrue(String name);
    
    Country findByCodeAndIdNotAndIsActiveTrue(String code,  int id);
    
    Country findByNameAndIdNotAndIsActiveTrue(String name, int id);
    
    Country findByIdAndIsActiveTrue(int countryId);
    Country findByNameAndCodeAndIsActiveFalse(String name, String code);
}
