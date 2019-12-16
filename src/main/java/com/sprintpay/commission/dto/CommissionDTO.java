/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sprintpay.commission.dto;

import com.sprintpay.commission.entities.CommissionNature;

/**
 *
 * @author ALINE-PSE
 */
public class CommissionDTO {
    public double commission;
    public int sourceGroupId; 
    public int destGroupId;
    public int transactionId;
    public double minAmount;
    public double maxAmount;
    public int commissionNatureId;
}
