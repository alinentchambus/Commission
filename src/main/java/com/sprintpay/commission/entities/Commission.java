/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author ALINE-PSE
 */
@Entity
public class Commission implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private double commission;
    private double minAmount;
    private double maxAmount;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "src_group_id")
    private Groupe sourceGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dest_group_id")
    private Groupe destinationGroup;

    
    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
    
    @ManyToOne
    @JoinColumn(name = "commission_nature_id")
    private CommissionNature commissionNature;

    public Commission() {
    }

    public Commission(double commission, double minAmount, double maxAmount) {
        this.commission = commission;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public CommissionNature getCommissionNature() {
        return commissionNature;
    }

    public void setCommissionNature(CommissionNature commissionNature) {
        this.commissionNature = commissionNature;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Groupe getSourceGroup() {
        return sourceGroup;
    }

    public void setSourceGroup(Groupe sourceGroup) {
        this.sourceGroup = sourceGroup;
    }

    public Groupe getDestinationGroup() {
        return destinationGroup;
    }

    public void setDestinationGroup(Groupe destinationGroup) {
        this.destinationGroup = destinationGroup;
    }

    @Override
    public String toString() {
        return "Commission{" + "id=" + id + ", commission=" + commission + ", minAmount=" + minAmount + ", maxAmount=" + maxAmount + ", sourceGroup=" + sourceGroup + ", destinationGroup=" + destinationGroup + ", transaction=" + transaction + ", commissionNature=" + commissionNature + '}';
    }

}
