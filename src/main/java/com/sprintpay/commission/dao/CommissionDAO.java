/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.dao;

import com.sprintpay.commission.entities.Commission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** 
 *
 * @author ALINE-PSE
 */
public interface CommissionDAO extends JpaRepository<Commission,Integer>{
    @Query("select c from Commission c where c.sourceGroup.id = :sourceGroupId and c.destinationGroup.id = :destinationGroupId "
            + "and c.transaction.code = :transactionCode and c.minAmount < :amount and c.maxAmount > :amount and c.isActive = true")
    Commission findCommission(@Param("sourceGroupId")int sourceGroupId, @Param("destinationGroupId")int destinationGroupId, @Param("transactionCode")String transactionCode,@Param("amount")double amount);

    
    @Query("select c from Commission c where c.sourceGroup.id = :sourceGroupId and c.destinationGroup.id = :destinationGroupId "
            + "and c.transaction.id = :transactionId and c.isActive = true")
    List<Commission> findBySrcGrpIdAndDestGrpIdAndTransactionId(@Param("sourceGroupId")int sourceGroupId, @Param("destinationGroupId")int destinationGroupId, @Param("transactionId")int transactionId);

    @Query("select c from Commission c where c.transaction.service.id = :serviceId and c.isActive = true")
    List<Commission> findByServiceId(@Param("serviceId")int serviceId);

}
