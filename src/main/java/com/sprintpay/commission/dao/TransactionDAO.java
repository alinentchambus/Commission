/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.dao;

import com.sprintpay.commission.entities.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ALINE-PSE
 */
public interface TransactionDAO extends JpaRepository<Transaction, Integer>{
    @Query("select t from Transaction t where t.service.id = :serviceId and t.isActive = true")
    List<Transaction> findByServiceId(@Param("serviceId")int serviceId);
    @Query("select t from Transaction t where t.isActive = true ")
    List<Transaction> findAllAndIsActiveTrue();
    Transaction findByNameAndIsActiveTrue(String name);
    Transaction findByNameAndIdNotAndIsActiveTrue(String name, int id);
    Transaction findByCodeAndIsActiveTrue(String code);
    Transaction findByCodeAndIdNotAndIsActiveTrue(String code, int id);
    Transaction findByIdAndIsActiveTrue(int id);
}
