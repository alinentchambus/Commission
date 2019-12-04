/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.entities;

import java.io.Serializable;
import javax.persistence.Entity;
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
    private int value;
    
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groupe group;
    
    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
    
    @ManyToOne
    @JoinColumn(name = "commission_nature_id")
    private CommissionNature commissionNature;

    public Commission() {
    }

    public Commission(int id, int value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Groupe getGroup() {
        return group;
    }

    public void setGroup(Groupe group) {
        this.group = group;
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

    @Override
    public String toString() {
        return "Commission{" + "id=" + id + ", value=" + value + ", group=" + group + '}';
    }
    
}
