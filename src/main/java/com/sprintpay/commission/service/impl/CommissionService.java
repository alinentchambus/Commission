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
import com.sprintpay.commission.exception.CommissionException;
import com.sprintpay.commission.service.ICommissionService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

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
        if (groupe.getId() > 0) {
            Groupe groupe1 = groupDAO.findByNameAndIdNotAndIsActiveTrue(groupe.getName(), groupe.getId());
            if (groupe1 != null) {
                throw new CommissionException("GROUP_NAME_EXIST");
            }
        } else {
            Groupe groupe1 = groupDAO.findByNameAndIsActiveTrue(groupe.getName());
            if (groupe1 != null) {
                throw new CommissionException("GROUP_NAME_EXIST");
            }
        }

        groupe.setIsActive(Boolean.TRUE);
        return groupDAO.save(groupe);
    }

    @Override
    public List<Groupe> findAllGroup() {
        return groupDAO.findAllAndIsActiveTrue();
    }

    @Override
    public void deleteGroup(int groupId) {
        Groupe groupe = groupDAO.findByIdAndIsActiveTrue(groupId);

        if (groupe != null) {
            groupe.setIsActive(Boolean.FALSE);
            groupDAO.save(groupe);
        }
        else{
            throw new CommissionException("GROUP_NOT_EXIST");
        }
    }

    @Override
    public Country saveOrUpdateCountry(CountryDTO countryDTO) {
        
        if(countryDTO.groupId == 0){
            throw new CommissionException("GROUP_ID_REQUIED");
        }
        Groupe group = groupDAO.findByIdAndIsActiveTrue(countryDTO.groupId);
        if(group == null){
            throw new CommissionException("INVALID_GROUP_ID");
        }
        
        if (countryDTO.id > 0) {
            Optional<Country> country = countryDAO.findById(countryDTO.id);
            if(!country.isPresent()){
                throw new CommissionException("INVALID_COUNTRY_ID");
            }
            Country testCountry = countryDAO.findByCodeAndIdNotAndIsActiveTrue(countryDTO.code, countryDTO.id);
            if (testCountry != null) {
                throw new CommissionException("COUNTRY_CODE_EXIST");
            }
            testCountry = countryDAO.findByNameAndIdNotAndIsActiveTrue(countryDTO.name, countryDTO.id);
            if (testCountry != null) {
                throw new CommissionException("COUNTRY_NAME_EXIST");
            }
        } else {
            Country testCountry = countryDAO.findByCodeAndIsActiveTrue(countryDTO.code);
            if (testCountry != null) {
                throw new CommissionException("COUNTRY_CODE_EXIST");
            }
            testCountry = countryDAO.findByNameAndIsActiveTrue(countryDTO.name);
            if (testCountry != null) {
                throw new CommissionException("COUNTRY_NAME_EXIST");
            }
        }
        
        Country deletedCountry = countryDAO.findByNameAndCodeAndIsActiveFalse(countryDTO.name, countryDTO.code);

        if(countryDTO.id == 0 && deletedCountry != null){
            deletedCountry.setGroup(group);
            deletedCountry.setIsActive(Boolean.TRUE);
            return countryDAO.save(deletedCountry);
        }else{
            Country country = new Country(countryDTO.id, countryDTO.code, countryDTO.name, true);
            country.setGroup(group);
            return countryDAO.save(country);
        }
    }

    @Override
    public List<Country> findCountryByGroupId(int groupId) {
        List<Country> countries = countryDAO.findByGroupeIdAndIsActiveTrue(groupId);
        return countries;
    }
    
    @Override
    public List<Country> findAllCountry(){
        return countryDAO.findAllAndIsActiveTrue();
    }

    @Override
    public void deleteCountry(int countryId) {
        Country country = countryDAO.findByIdAndIsActiveTrue(countryId);

        if (country != null) {
            country.setIsActive(Boolean.FALSE);
            countryDAO.save(country);
        }
        else{
            throw new CommissionException("COUNTRY_NOT_EXIST");
        }
    }

    @Override
    public List<com.sprintpay.commission.entities.Service> findAllService() {
        return serviceDAO.findAllAndIsActiveTrue();
    }

    @Override
    public com.sprintpay.commission.entities.Service saveOrUpdateService(com.sprintpay.commission.entities.Service service) {

        if (service.getId() > 0) {
            com.sprintpay.commission.entities.Service service1 = serviceDAO.findByNameAndIdNotAndIsActiveTrue(service.getName(), service.getId());
            if (service1 != null) {
                throw new CommissionException("SERVICE_NAME_EXIST");
            }
        } else {
            com.sprintpay.commission.entities.Service service1 = serviceDAO.findByNameAndIsActiveTrue(service.getName());
            if (service1 != null) {
                throw new CommissionException("SERVICE_NAME_EXIST");
            }
        }
        service.setIsActive(Boolean.TRUE);
        return serviceDAO.save(service);
    }

    @Override
    public void deleteService(int serviceId) {
        com.sprintpay.commission.entities.Service service = serviceDAO.findByIdAndIsActiveTrue(serviceId);
        if (service != null) {
            service.setIsActive(Boolean.FALSE);
            serviceDAO.save(service);
        }
        else{
            throw new CommissionException("SERVICE_NOT_EXIST");
        }
    }

    @Override
    public List<Transaction> findTransactionByServiceId(int serviceId) {
        return transactionDAO.findByServiceId(serviceId);
    }
    
    @Override
    public List<Transaction> findAllTransaction(){
        return transactionDAO.findAllAndIsActiveTrue();
    }

    @Override
    public Transaction saveOrUpdateTransaction(TransactionDTO transactionDTO) {

        if(transactionDTO.code == null){
            
            throw new CommissionException("TRANSACTION_CODE_REQUIRED");
        }
        if(transactionDTO.serviceId == 0){
            throw new CommissionException("SERVICE_ID_REQUIRED");
        }
        com.sprintpay.commission.entities.Service service = serviceDAO.findByIdAndIsActiveTrue(transactionDTO.serviceId);
        if(service == null){
            throw new CommissionException("INVALID_SERVICE_ID");
        }
        if (transactionDTO.id > 0) {
            Optional<Transaction> transaction = transactionDAO.findById(transactionDTO.id);
            if(!transaction.isPresent()){
                throw new CommissionException("INVALID_TRANSACTION_ID");
            }
            Transaction testTransaction = transactionDAO.findByCodeAndIdNotAndIsActiveTrue(transactionDTO.code, transactionDTO.id);
            if (testTransaction != null) {
                throw new CommissionException("TRANSACTION_CODE_EXIST");
            }
            testTransaction = transactionDAO.findByNameAndIdNotAndIsActiveTrue(transactionDTO.name, transactionDTO.id);
            if (testTransaction != null) {
                throw new CommissionException("TRANSACTION_NAME_EXIST");
            }
        } else {
            Transaction transaction = transactionDAO.findByCodeAndIsActiveTrue(transactionDTO.code);
            if (transaction != null) {
                throw new CommissionException("TRANSACTION_CODE_EXIST");
            }
            transaction = transactionDAO.findByNameAndIsActiveTrue(transactionDTO.name);
            if (transaction != null) {
                throw new CommissionException("TRANSACTION_NAME_EXIST");
            }
        }
        
        Transaction deletedTransaction = transactionDAO.findByNameAndCodeAndIsActiveFalse(transactionDTO.name, transactionDTO.code);

        if(transactionDTO.id == 0 && deletedTransaction != null){
            deletedTransaction.setService(service);
            deletedTransaction.setIsActive(Boolean.TRUE);
            return transactionDAO.save(deletedTransaction);
        }else{
                Transaction transaction = new Transaction(transactionDTO.id, transactionDTO.code, transactionDTO.name, transactionDTO.description, true);
            transaction.setService(service);
            return transactionDAO.save(transaction);
        }


    }

    @Override
    public void deleteTransaction(@PathVariable int transactionId) {
        Transaction transaction = transactionDAO.findByIdAndIsActiveTrue(transactionId);

        if (transaction != null) {
            transaction.setIsActive(Boolean.FALSE);
            transactionDAO.save(transaction);
        }
        else{
            throw new CommissionException("TRANSACTION_NOT_EXIST");
        }
    }

    @Override
    public List<CommissionNature> getAllCommissionNature() {
        return commissionNatureDAO.findAll();
    }

    @Override
    public Commission saveOrUpdateCommission(CommissionDTO commissionDTO) {

        List<Commission> com_tests = commissionDAO.findBySrcGrpIdAndDestGrpIdAndTransactionId(commissionDTO.sourceGroupId, commissionDTO.destGroupId, commissionDTO.transactionId);
        if (com_tests != null && !com_tests.isEmpty()) {
            for (Commission com_test : com_tests) {
                System.out.println();
                System.out.println("dans la bouble");
                if ((commissionDTO.maxAmount > com_test.getMinAmount() && commissionDTO.maxAmount < com_test.getMaxAmount())
                        || (commissionDTO.minAmount > com_test.getMinAmount() && commissionDTO.minAmount < com_test.getMaxAmount())
                        || (commissionDTO.maxAmount == com_test.getMinAmount() && commissionDTO.maxAmount == com_test.getMaxAmount())) {
                    throw new CommissionException("CONFLICTING_CONFIG");
                }
            }
        }
        Groupe srcGroup = groupDAO.findByIdAndIsActiveTrue(commissionDTO.sourceGroupId);
        Groupe destGroup = groupDAO.findByIdAndIsActiveTrue(commissionDTO.destGroupId);
        Transaction transaction = transactionDAO.findByIdAndIsActiveTrue(commissionDTO.transactionId);
        CommissionNature commissionNature = commissionNatureDAO.findById(commissionDTO.commissionNatureId).get();

        Commission commission = new Commission(commissionDTO.commission, commissionDTO.minAmount, commissionDTO.maxAmount, true);
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
    public double findCommission(String srcCountryCode, String destCountryCode, String transactionCode, double amount) {
        double commissionAmount = 0.0;
        Groupe srcGroup = groupDAO.findByCountryCodeAndIsActiveTrue(srcCountryCode);
        Groupe destGroup = groupDAO.findByCountryCodeAndIsActiveTrue(destCountryCode);

        if (srcGroup != null && destGroup != null) {
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
