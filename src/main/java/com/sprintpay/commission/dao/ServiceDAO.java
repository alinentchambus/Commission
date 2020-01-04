/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.dao;

import com.sprintpay.commission.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ALINE-PSE
 */
public interface ServiceDAO extends JpaRepository<Service, Integer >{
    Service findByName(String name);
    Service findByNameAndIdNot(String name, int id);
}
