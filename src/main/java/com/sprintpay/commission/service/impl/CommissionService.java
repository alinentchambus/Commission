/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.service.impl;

import com.sprintpay.commission.dao.CountryDAO;
import com.sprintpay.commission.dao.GroupDAO;
import com.sprintpay.commission.dao.ServiceDAO;
import com.sprintpay.commission.dao.TransactionDAO;
import com.sprintpay.commission.entities.Country;
import com.sprintpay.commission.entities.Groupe;
import com.sprintpay.commission.entities.Transaction;
import com.sprintpay.commission.service.ICommissionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ALINE-PSE
 */
@Service
@Transactional
public class CommissionService implements ICommissionService {

    @Autowired
    private GroupDAO groupDAO;
    @Autowired
    private CountryDAO countryDAO;
    @Autowired
    private ServiceDAO serviceDAO;
    @Autowired
    private TransactionDAO transactionDAO;

    @Override
    public Groupe saveOrUpdateGroup(Groupe groupe) {
        return groupDAO.save(groupe);
    }

    @Override
    public List<Groupe> findAllGroup() {
        return groupDAO.findAll();
    }

    @Override
    public void deleteGroup(int groupId) {
        groupDAO.findById(groupId);
    }
    
    @Override
    public Country saveOrUpdateCountry(Country country) {
        return countryDAO.save(country);
    }

    @Override
    public List<Country> findCountryByGroupId(int groupId) {
        return countryDAO.findByGroupeId(groupId);
    }

    @Override
    public void deleteCountry(int countryId) {
        countryDAO.findById(countryId);
    }
    
    @Override
    public List<com.sprintpay.commission.entities.Service> findAllService() {
        return  serviceDAO.findAll();
    }

    @Override
    public com.sprintpay.commission.entities.Service saveOrUpdateService(com.sprintpay.commission.entities.Service service) {
        return serviceDAO.save(service);
    }

    @Override
    public void deleteService(int serviceId) {
        serviceDAO.deleteById(serviceId);
    }

    
    @Override
    public List<Transaction> findByServiceId(int serviceId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Transaction saveOrUpdateTransaction(Transaction transaction) {
        return transactionDAO.save(transaction);
    }

    @Override
    public void deleteTransaction(int transaction) {
        transactionDAO.deleteById(transaction);
    }

}
