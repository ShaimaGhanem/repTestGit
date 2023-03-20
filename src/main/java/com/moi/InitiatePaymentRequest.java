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
/* "initiatePaymentRequest": {
        "civilId": "",
        "totalPayAmt": "",
        "language": "",
        "serviceType": "",
        "paymentList": [
            {
                "itemNumber": "",
                "year": "",
                "amount": "",
                "descriptionEn": "",
                "descriptionAr": "",
                "field1": "",
                "field2": "",
                "field3": "",
                "field4": "",
                "field5": "",
                "field6": ""
            }
        ]
    }*/
public class InitiatePaymentRequest {

   
    private String applicationName = "";
    private String amount = "";
    private String recieptUrl = "";
    private String errorUrl = "";
    private String serviceType="";
    private String language="EN";

    /**
     * @return the serviceType
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * @param serviceType the serviceType to set
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }
    
    
    
  

    /**
     * @return the applicationName
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * @param applicationName the applicationName to set
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the recieptUrl
     */
    public String getRecieptUrl() {
        return recieptUrl;
    }

    /**
     * @param recieptUrl the recieptUrl to set
     */
    public void setRecieptUrl(String recieptUrl) {
        this.recieptUrl = recieptUrl;
    }

    /**
     * @return the errorUrl
     */
    public String getErrorUrl() {
        return errorUrl;
    }

    /**
     * @param errorUrl the errorUrl to set
     */
    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }
    
    
}
