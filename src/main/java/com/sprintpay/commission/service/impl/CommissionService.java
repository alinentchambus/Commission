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
            Groupe groupe1 = groupDAO.findByNameAndIdNot(groupe.getName(), groupe.getId());
            if (groupe1 != null) {
                throw new CommissionException("GROUP_NAME_EXIST");
            }
        } else {
            Groupe groupe1 = groupDAO.findByName(groupe.getName());
            if (groupe1 != null) {
                throw new CommissionException("GROUP_NAME_EXIST");
            }
        }

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

        if (countryDTO.id > 0) {
            Country country = countryDAO.findByCodeAndIdNot(countryDTO.code, countryDTO.id);
            if (country != null) {
                throw new CommissionException("COUNTRY_CODE_EXIST");
            }
            country = countryDAO.findByNameAndIdNot(countryDTO.name, countryDTO.id);
            if (country != null) {
                throw new CommissionException("COUNTRY_NAME_EXIST");
            }
        } else {
            Country country = countryDAO.findByCode(countryDTO.code);
            if (country != null) {
                throw new CommissionException("COUNTRY_CODE_EXIST");
            }
            country = countryDAO.findByName(countryDTO.name);
            if (country != null) {
                throw new CommissionException("COUNTRY_NAME_EXIST");
            }
        }

        Groupe groupe = groupDAO.findById(countryDTO.groupId).get();
        Country country = new Country(countryDTO.id, countryDTO.code, countryDTO.name);
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

        if (service.getId() > 0) {
            com.sprintpay.commission.entities.Service service1 = serviceDAO.findByNameAndIdNot(service.getName(), service.getId());
            if (service1 != null) {
                throw new CommissionException("SERVICE_NAME_EXIST");
            }
        } else {
            com.sprintpay.commission.entities.Service service1 = serviceDAO.findByName(service.getName());
            if (service1 != null) {
                throw new CommissionException("SERVICE_NAME_EXIST");
            }
        }
        return serviceDAO.save(service);
    }

    @Override
    public void deleteService(int serviceId) {
        serviceDAO.deleteById(serviceId);
    }

    @Override
    public List<Transaction> findTransactionByServiceId(int serviceId) {
        return transactionDAO.findByServiceId(serviceId);
    }

    @Override
    public Transaction saveOrUpdateTransaction(TransactionDTO transactionDTO) {

        if (transactionDTO.id > 0) {
            Transaction transaction = transactionDAO.findByCodeAndIdNot(transactionDTO.code, transactionDTO.id);
            if (transaction != null) {
                throw new CommissionException("TRANSACTION_CODE_EXIST");
            }
            transaction = transactionDAO.findByNameAndIdNot(transactionDTO.name, transactionDTO.id);
            if (transaction != null) {
                throw new CommissionException("TRANSACTION_NAME_EXIST");
            }
        } else {
            Transaction transaction = transactionDAO.findByCode(transactionDTO.code);
            if (transaction != null) {
                throw new CommissionException("TRANSACTION_CODE_EXIST");
            }
            transaction = transactionDAO.findByName(transactionDTO.name);
            if (transaction != null) {
                throw new CommissionException("TRANSACTION_NAME_EXIST");
            }
        }

        com.sprintpay.commission.entities.Service service = serviceDAO.findById(transactionDTO.serviceId).get();
        Transaction transaction = new Transaction(transactionDTO.id, transactionDTO.code, transactionDTO.name, transactionDTO.description);
        if (service != null) {
            transaction.setService(service);
        }
        return transactionDAO.save(transaction);
    }

    @Override
    public void deleteTransaction(@PathVariable int transactionId) {
        transactionDAO.deleteById(transactionId);
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
                if ((commissionDTO.maxAmount > com_test.getMinAmount() && commissionDTO.maxAmount < com_test.getMaxAmount()) || 
                        (commissionDTO.minAmount > com_test.getMinAmount() && commissionDTO.minAmount < com_test.getMaxAmount()) ||
                        (commissionDTO.maxAmount == com_test.getMinAmount() && commissionDTO.maxAmount == com_test.getMaxAmount())) {
                    throw new CommissionException("CONFLICTING_CONFIG");
                }
            }
        }
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
    public double findCommission(String srcCountryCode, String destCountryCode, String transactionCode, double amount) {
        double commissionAmount = 0.0;
        Groupe srcGroup = groupDAO.findByCountryCode(srcCountryCode);
        Groupe destGroup = groupDAO.findByCountryCode(destCountryCode);

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
