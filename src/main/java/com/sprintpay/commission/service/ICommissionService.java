/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.service;

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
    
    //Country
    Country saveOrUpdateCountry(Country country);
    List<Country> findCountryByGroupId(int groupId);
    void deleteCountry(int countryId);
    
    //Services
    List<Service> findAllService();
    Service saveOrUpdateService(Service service);
    void deleteService(int serviceId);
    
    //Transactions
    List<Transaction> findByServiceId(int serviceId);
    Transaction saveOrUpdateTransaction(Transaction transaction);
    void deleteTransaction(int transaction);
    
}
