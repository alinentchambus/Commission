/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.dao;

import com.sprintpay.commission.entities.CommissionNature;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ALINE-PSE
 */
public interface CommissionNatureDAO extends JpaRepository<CommissionNature,Integer>{
    
}
