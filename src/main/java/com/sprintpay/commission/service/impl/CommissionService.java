/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.service.impl;

import com.sprintpay.commission.dao.CommissionDAO;
import com.sprintpay.commission.dao.CommissionNatureDAO;
import com.sprintpay.commission.dao.CountryDAO;
import com.sprintpay.commission.dao.GroupDAO;
import com.sprintpay.commission.dao.ServiceDAO;
import com.sprintpay.commission.dao.TransactionDAO;
import com.sprintpay.commission.dto.CommissionDTO;
import com.sprintpay.commission.dto.CountryDTO;
import com.sprintpay.commission.dto.TransactionDTO;
import com.sprintpay.commission.entities.Commission;
import com.sprintpay.commission.entities.CommissionNature;
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
    @Autowired
    private CommissionNatureDAO commissionNatureDAO;
    @Autowired
    private CommissionDAO commissionDAO;

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
        groupDAO.deleteById(groupId);
    }

    @Override
    public Country saveOrUpdateCountry(CountryDTO countryDTO) {

        Groupe groupe = groupDAO.findById(countryDTO.groupId).get();
        Country country = new Country(countryDTO.code, countryDTO.name);
        if (groupe != null) {
            country.setGroup(groupe);
        }
        return countryDAO.save(country);
    }

    @Override
    public List<Country> findCountryByGroupId(int groupId) {
        List<Country> countries = countryDAO.findByGroupeId(groupId);
        return countries;
    }

    @Override
    public void deleteCountry(int countryId) {
        countryDAO.deleteById(countryId);
    }

    @Override
    public List<com.sprintpay.commission.entities.Service> findAllService() {
        return serviceDAO.findAll();
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
        return transactionDAO.findByServiceId(serviceId);
    }

    @Override
    public Transaction saveOrUpdateTransaction(TransactionDTO transactionDTO) {
        com.sprintpay.commission.entities.Service service = serviceDAO.findById(transactionDTO.serviceId).get();
        Transaction transaction = new Transaction(transactionDTO.name, transactionDTO.description);
        if (service != null) {
            transaction.setService(service);
        }
        return transactionDAO.save(transaction);
    }

    @Override
    public void deleteTransaction(int transactionId) {
        transactionDAO.deleteById(transactionId);
    }

    @Override
    public List<CommissionNature> getAllCommissionNature() {
        return commissionNatureDAO.findAll();
    }

    @Override
    public Commission saveOrUpdateCommission(CommissionDTO commissionDTO) {
        Groupe srcGroup = groupDAO.findById(commissionDTO.sourceGroupId).get();
        Groupe destGroup = groupDAO.findById(commissionDTO.destGroupId).get();
        Transaction transaction = transactionDAO.findById(commissionDTO.transactionId).get();
        CommissionNature commissionNature = commissionNatureDAO.findById(commissionDTO.commissionNatureId).get();

        Commission commission = new Commission(commissionDTO.commission, commissionDTO.minAmount, commissionDTO.maxAmount);
        if (srcGroup != null) {
            commission.setSourceGroup(srcGroup);
        }

        if (destGroup != null) {
            commission.setDestinationGroup(destGroup);
        }

        if (transaction != null) {
            commission.setTransaction(transaction);
        }

        if (commissionNature != null) {
            commission.setCommissionNature(commissionNature);
        }
        
        return commissionDAO.save(commission);
    }

    @Override
    public double findCommission(String srcCountryCode, String destCountryCode, String transactionCode, int amount) {
        double commissionAmount = 0.0;
        Groupe srcGroup = groupDAO.findByCountryCode(srcCountryCode);        
        Groupe destGroup = groupDAO.findByCountryCode(destCountryCode);

        Country srcCountry = countryDAO.findByCode(srcCountryCode);
        Country destCountry = countryDAO.findByCode(destCountryCode);
        System.out.println();        
        System.out.println(srcGroup);
        System.out.println(destGroup);


        if (srcGroup != null && destGroup != null ) {
            Commission commission = commissionDAO.findCommission(srcGroup.getId(), destGroup.getId(), transactionCode, amount);

            if (commission != null) {
                CommissionNature commissionNature = commissionNatureDAO.findById(commission.getCommissionNature().getId()).get();

                if (commissionNature != null) {
                    if (commissionNature.getName().equals("valeur")) {
                        commissionAmount = commission.getCommission();
                    } else {
                        commissionAmount = (commission.getCommission() * amount) / 100;
                    }
                }
            }
        }
        
        return commissionAmount;
    }

}
