/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.web.parameters;

/**
 *
 * @author ALINE-PSE
 */
public class CommissionParams {
    private String srcCountryCode;
    private String destCountryCode;
    private String transactionCode;
    private int amount;

    public String getSrcCountryCode() {
        return srcCountryCode;
    }

    public void setSrcCountryCode(String srcCountryCode) {
        this.srcCountryCode = srcCountryCode;
    }

    public String getDestCountryCode() {
        return destCountryCode;
    }

    public void setDestCountryCode(String destCountryCode) {
        this.destCountryCode = destCountryCode;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
}
