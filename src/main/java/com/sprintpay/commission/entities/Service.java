/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ALINE-PSE
 */
@Entity
public class Service implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    
    @OneToMany(mappedBy = "service")
    private List<Transaction> transactions;

    public Service() {
    }

    public Service(int id, String name, String description, List<Transaction> transactions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.transactions = transactions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", name=" + name + ", description=" + description + ", transactions=" + transactions + '}';
    }
}
