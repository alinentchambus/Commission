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
    
    @Query("select * from Country where Country.group.id = :groupId")
    List<Country> findByGroupeId(@Param("groupId") int groupId);
}
