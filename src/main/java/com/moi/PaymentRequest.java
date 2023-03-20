/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moi;

/**
 *
 * @author MOI
 */
public class PaymentRequest {

   
    
     private String applicationId = "";//applicationName
    private String gatewayLanguage = "EN";//language
    private String userCivilId = "";
    private String payForCivilId = "";
    private String serviceTypeId = "";//serviceType
    private String totalAmount = "";//amount
    private String successUrl = "";//recieptUrl
    private String failureUrl = "";//errorUrl
    /**
     * @return the applicationId
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * @param applicationId the applicationId to set
     */
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * @return the gatewayLanguage
     */
    public String getGatewayLanguage() {
        return gatewayLanguage;
    }

    /**
     * @param gatewayLanguage the gatewayLanguage to set
     */
    public void setGatewayLanguage(String gatewayLanguage) {
        this.gatewayLanguage = gatewayLanguage;
    }

    /**
     * @return the userCivilId
     */
    public String getUserCivilId() {
        return userCivilId;
    }

    /**
     * @param userCivilId the userCivilId to set
     */
    public void setUserCivilId(String userCivilId) {
        this.userCivilId = userCivilId;
    }

    /**
     * @return the payForCivilId
     */
    public String getPayForCivilId() {
        return payForCivilId;
    }

    /**
     * @param payForCivilId the payForCivilId to set
     */
    public void setPayForCivilId(String payForCivilId) {
        this.payForCivilId = payForCivilId;
    }

    /**
     * @return the serviceTypeId
     */
    public String getServiceTypeId() {
        return serviceTypeId;
    }

    /**
     * @param serviceTypeId the serviceTypeId to set
     */
    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    /**
     * @return the totalAmount
     */
    public String getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return the successUrl
     */
    public String getSuccessUrl() {
        return successUrl;
    }

    /**
     * @param successUrl the successUrl to set
     */
    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    /**
     * @return the failureUrl
     */
    public String getFailureUrl() {
        return failureUrl;
    }

    /**
     * @param failureUrl the failureUrl to set
     */
    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }
    
    
    
    
   
   
}
