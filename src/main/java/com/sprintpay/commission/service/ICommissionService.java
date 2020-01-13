/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.service;

import com.sprintpay.commission.dto.CommissionDTO;
import com.sprintpay.commission.dto.CountryDTO;
import com.sprintpay.commission.dto.TransactionDTO;
import com.sprintpay.commission.entities.Commission;
import com.sprintpay.commission.entities.CommissionNature;
import com.sprintpay.commission.entities.Country;
import com.sprintpay.commission.entities.Groupe;
import com.sprintpay.commission.entities.Service;
import com.sprintpay.commission.entities.Transaction;
import java.util.List;

/**
 *
 * @author ALINE-PSE
 */
public interface ICommissionService {
    //Group
    Groupe saveOrUpdateGroup(Groupe groupe);
    List<Groupe> findAllGroup();
    void deleteGroup(int groupId);
    //void commissionConfig
    
    //Country
    Country saveOrUpdateCountry(CountryDTO country);
    List<Country> findCountryByGroupId(int groupId);    
    List<Country> findAllCountry();
    void deleteCountry(int countryId);
    
    //Services
    List<Service> findAllService();
    Service saveOrUpdateService(Service service);
    void deleteService(int serviceId);
    
    //Transactions
    List<Transaction> findTransactionByServiceId(int serviceId);    
    List<Transaction> findAllTransaction();

    Transaction saveOrUpdateTransaction(TransactionDTO transactionDTO);
    void deleteTransaction(int transaction);
    
    
    //CommissionNature
    List<CommissionNature> getAllCommissionNature();
    Commission saveOrUpdateCommission(CommissionDTO commissionDTO);
    double findCommission(String srcCountryCode, String destCountryCode, String transactionCode, double amount);
}
