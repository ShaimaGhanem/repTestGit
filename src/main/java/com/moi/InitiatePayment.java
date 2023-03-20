/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.moi;

import com.fss.plugin.iPayPipe;
import com.google.gson.Gson;
import java.io.BufferedReader;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@WebServlet("/")
public class InitiatePayment extends HttpServlet {

    /**
     * @return the trackID
     */
    public String getTrackID() {
        return trackID;
    }

    /**
     * @param trackID the trackID to set
     */
    public void setTrackID(String trackID) {
        this.trackID = trackID;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the paymentID
     */
    public String getPaymentID() {
        return paymentID;
    }

    /**
     * @param paymentID the paymentID to set
     */
    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    /**
     * @return the transID
     */
    public String getTransID() {
        return transID;
    }

    /**
     * @param transID the transID to set
     */
    public void setTransID(String transID) {
        this.transID = transID;
    }

    /**
     * @return the refID
     */
    public String getRefID() {
        return refID;
    }

    /**
     * @param refID the refID to set
     */
    public void setRefID(String refID) {
        this.refID = refID;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return the authCode
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * @param authCode the authCode to set
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    /**
     * @return the serviceExecStatus
     */
    public String getServiceExecStatus() {
        return serviceExecStatus;
    }

    /**
     * @param serviceExecStatus the serviceExecStatus to set
     */
    public void setServiceExecStatus(String serviceExecStatus) {
        this.serviceExecStatus = serviceExecStatus;
    }

    /**
     * @return the errorID
     */
    public String getErrorID() {
        return errorID;
    }

    /**
     * @param errorID the errorID to set
     */
    public void setErrorID(String errorID) {
        this.errorID = errorID;
    }

    /**
     * @return the errorText
     */
    public String getErrorText() {
        return errorText;
    }

    /**
     * @param errorText the errorText to set
     */
    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    static final FileParameters fileParameters = new FileParameters();

    static {
        try {
            fileParameters.readParameters();
        } catch (IOException ex) {
            // Logger.getLogger(InitiatePayment.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(InitiatePayment.class.getName() + ex.getMessage());
        }
    }

    /**
     * @return the paymentURL
     */
    public String getPaymentURL() {
        return paymentURL;
    }

    /**
     * @param paymentURL the paymentURL to set
     */
    public void setPaymentURL(String paymentURL) {
        this.paymentURL = paymentURL;
    }

    /**
     * @return the applicationID
     */
    public String getApplicationID() {
        return applicationID;
    }

    /**
     * @param applicationID the applicationID to set
     */
    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
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
     * @return the serviceTypeID
     */
    public String getServiceTypeID() {
        return serviceTypeID;
    }

    /**
     * @param serviceTypeID the serviceTypeID to set
     */
    public void setServiceTypeID(String serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }

    /**
     * @return the userCivilID
     */
    public String getUserCivilID() {
        return userCivilID;
    }

    /**
     * @param userCivilID the userCivilID to set
     */
    public void setUserCivilID(String userCivilID) {
        this.userCivilID = userCivilID;
    }

    /**
     * @return the payForCivilID
     */
    public String getPayForCivilID() {
        return payForCivilID;
    }

    /**
     * @param payForCivilID the payForCivilID to set
     */
    public void setPayForCivilID(String payForCivilID) {
        this.payForCivilID = payForCivilID;
    }

    /**
     * @return the noOfItems
     */
    public int getNoOfItems() {
        return noOfItems;
    }

    /**
     * @param noOfItems the noOfItems to set
     */
    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    private String applicationID = "";
    private String amount = "";
    private String language = ""; //English – “EN”, Arabic – “AR”
    private String serviceTypeID = "";
    private String userCivilID = "";
    private String payForCivilID = "";
    private int noOfItems = -1;
    private List<Item> mItemList = null;
    private String paymentURL = null;
    private String trackID = "";
    private String status = "";
    private String paymentID = "";
    private String transID = "";
    private String refID = "";
    private String result = "";
    private String authCode = "";
    private String serviceExecStatus = "";
    private String errorID = "";
    private String errorText = "";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            System.out.println("--------------------do post------- knet post--------------------------------");

            //get required data from body
            Gson gson = new Gson();
            String data;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            data = builder.toString();
            System.out.println("value of request data is : " + data);

            InitRequest requestData = gson.fromJson(data, InitRequest.class);
            mItemList = requestData.getItemList();
            System.out.println("-----1------------" + data);

            //Merchant can connect iPay Plugin using below step:
            iPayPipe pipe = new iPayPipe();
            //Initialization

            Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
            String filePath = path.toString() + "//ResourceFiles";
            //unique data for each service type will get it from table according to service type
            //String resourcePath = fileParameters.getResourcePath();
            //String keystorePath = fileParameters.getKeystorePath();

            setApplicationID(requestData.getPaymentRequest().getApplicationId());
            setAmount(requestData.getPaymentRequest().getTotalAmount());
            setLanguage(requestData.getPaymentRequest().getGatewayLanguage()); //English – “EN”, Arabic – “AR”
            setServiceTypeID(requestData.getPaymentRequest().getServiceTypeId());
            setUserCivilID(requestData.getPaymentRequest().getUserCivilId());
            setPayForCivilID(requestData.getPaymentRequest().getPayForCivilId());
            setNoOfItems(requestData.getItemList().size());

            String applicationName;
            String recieptURL;
            String errorURL;
            String serviceTypeNameEn;
            String serviceTypeNameAr;
            String aliasName;
            String terminalID;

            String soapEndpointUrl;
            String soapAction;
            SOAPClientSAAJ mSOAPClientSAAJ;
            SOAPMessage soapResponse;
            Iterator itr;
            soapEndpointUrl = fileParameters.getPaymentIp() + ":" + fileParameters.getPaymentPort() + "/PY003/LIST_APPLICATION_CODES";

            soapAction = "http://tempuri.org/ListApplicationCodescall/";
            mSOAPClientSAAJ = new SOAPClientSAAJ();

            System.out.println("before calling callSoapWebService");
            soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, "", 0);

            System.out.println("after calling callSoapWebService");
            System.out.println("  Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();

            itr = soapResponse.getSOAPBody().getChildElements();

            List<String> applicationDetails;
            System.out.println("  before forming the output:");
            applicationDetails = ReadXMLApplicationCodes(itr);
            applicationName = applicationDetails.get(0);
            recieptURL = applicationDetails.get(1);
            if (recieptURL.isEmpty()) {
                recieptURL = fileParameters.getDefaultRecieptURL();
            }
            errorURL = applicationDetails.get(2);
            if (errorURL.isEmpty()) {
                errorURL = fileParameters.getDefaultErrorURL();
            }
            soapEndpointUrl = fileParameters.getPaymentIp() + ":" + fileParameters.getPaymentPort() + "/PY001/LIST_PAYMENT_SERVICE_TYPES";

            soapAction = "http://tempuri.org/ListPaymentServiceTypescall/";
            mSOAPClientSAAJ = new SOAPClientSAAJ();

            System.out.println("before calling callSoapWebService");
            soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, "", 1);
            System.out.println("after calling callSoapWebService");
            System.out.println("  Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();

            itr = soapResponse.getSOAPBody().getChildElements();

            List<String> serviceTypeDetails;
            System.out.println("  before forming the output:");
            serviceTypeDetails = ReadXMLServiceTypes(itr);
            serviceTypeNameAr = serviceTypeDetails.get(0);
            serviceTypeNameEn = serviceTypeDetails.get(1);
            aliasName = serviceTypeDetails.get(2);
            terminalID = serviceTypeDetails.get(3);

            //constant data will not changed
            String action = "1"; //1–Purchase
            String currency = "414"; //Transaction Currency (ex: “414”)

            System.out.println("----------------2-----------");
            //data generated : create trackid to use it as per knet request to follow the request
            Random rnd = new Random(System.currentTimeMillis());
            /* String trackID = String.valueOf(Math.abs(rnd.nextLong()));*/
            //String trackID = strackID.substring(0, 18);  

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String lastUpdated = dtf.format(now);
            System.out.println("------------3---------------");
            //User Defined Fields
            String Udf1 = applicationID;//application ID
            //String Udf1 = ApplicationName;

            String Udf2 = userCivilID;//user civil id
            //String Udf2 = civilID;

            String Udf3 = payForCivilID; // pay for civil id
            //String Udf3 = mobileNumber;

            String Udf4 = "Udf4";
            String Udf5 = "Udf5";

            soapEndpointUrl = fileParameters.getPaymentIp() + ":" + fileParameters.getPaymentPort() + "/PY005/RECORD_PAYMENT_TRANSACTION";

            soapAction = "http://tempuri.org/RecordPaymentTransactioncall/";
            mSOAPClientSAAJ = new SOAPClientSAAJ();
            String xmlString = formXMLTransaction("INITIALIZED");
            System.out.println("---------2----xml to log data to db :" + xmlString);
            System.out.println("before calling callSoapWebService");
            soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, xmlString, 2);
            System.out.println("after calling callSoapWebService");
            System.out.println("  Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();

            itr = soapResponse.getSOAPBody().getChildElements();

            System.out.println("  before forming the output:");
            String returnCode = getXMLResult(itr);
            if (!returnCode.equals("1")) {

                System.err.println("\ncan't insert initialization record in transactions table\n");
                //fail database record not inserted

            } else {

                //Set Values
                pipe.setResourcePath(filePath);
                System.out.println("------------resourcePath---------------" + filePath);
                pipe.setKeystorePath(filePath);
                System.out.println("------------keystorePath---------------" + filePath);
                pipe.setAlias(aliasName);
                System.out.println("------------aliasName---------------" + aliasName);
                pipe.setAction(action);
                System.out.println("------------action---------------" + action);
                pipe.setCurrency(currency);
                System.out.println("------------currency---------------" + currency);
                pipe.setLanguage(language);
                System.out.println("------------language---------------" + language);
                pipe.setResponseURL("http://10.11.20.70:9080/KNetIntegration/HandleResponse");
                System.out.println("------------recieptURL---------------" + "http://10.11.20.70:9080/KNetIntegration/HandleResponse");
                pipe.setErrorURL("http://10.11.20.70:9080/KNetIntegration/HandleResponse");
                System.out.println("------------errorURL---------------" + "http://10.11.20.70:9080/KNetIntegration/HandleResponse");
                pipe.setAmt(amount);
                System.out.println("------------amount---------------" + amount);
                pipe.setTrackId(getTrackID());
                System.out.println("------------trackid---------------" + trackID);
                pipe.setUdf1(Udf1);
                System.out.println("------------Udf1---------------" + Udf1);
                pipe.setUdf2(Udf2);
                System.out.println("------------Udf2---------------" + Udf2);
                pipe.setUdf3(Udf3); //8 digit mobile number to be sent in UDF3 for KFast.
                System.out.println("------------Udf3---------------" + Udf3);
                pipe.setUdf4(Udf4);
                System.out.println("------------Udf4---------------" + Udf4);
                pipe.setUdf5(Udf5);
                System.out.println("------------Udf5---------------" + Udf5);
                System.out.println("abefore performPaymentInitializationHTTP");
                // For Hosted Payment Integration, the method to be called is
                Integer val = pipe.performPaymentInitializationHTTP();
                String result;
                String resultMessage;
                System.out.println("after performPaymentInitializationHTTP  val is " + val);
                if (val == 0) {

                    System.out.println("---------1-----");
                    result = pipe.getWebAddress();
                    setPaymentURL(result);
                    resultMessage = "Success";
                    System.out.println("---------2-----the web address :" + result);

                    soapEndpointUrl = fileParameters.getPaymentIp() + ":" + fileParameters.getPaymentPort() + "/PY006/UPDATE_PAYMENT_TRANSACTION";
                    soapAction = "http://tempuri.org/UpdatePaymentTransactioncall/";
                    mSOAPClientSAAJ = new SOAPClientSAAJ();
                    xmlString = formXMLTransaction("PROCESSING");
                    System.out.println("---------2----xml to log data to db :" + xmlString);
                    System.out.println("before calling callSoapWebService");
                    soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, xmlString, 3);
                    System.out.println("after calling callSoapWebService");
                    System.out.println("  Response SOAP Message:");
                    soapResponse.writeTo(System.out);
                    System.out.println();
                    itr = soapResponse.getSOAPBody().getChildElements();
                    System.out.println("  before forming the output:");
                    returnCode = getXMLResult(itr);
                    if (!returnCode.equals("1")) {
                        System.err.println("\ncan't insert processing record in transactions table\n");
                        //fail database record not inserted
                    } else {

                        /*    //store data in the table knet_transactions
                try {
                    ////////////////////////////////////////to be added/////////////////////////////////////////////////////
                    //insert in transactions http://10.10.1.1:29081/PY005/RECORD_PAYMENT_TRANSACTION
                
                soapEndpointUrl = fileParameters.getPaymentIp() + ":" + fileParameters.getPaymentPort() + "/PY003/LIST_APPLICATION_CODES";

            soapAction = "http://tempuri.org/ListApplicationCodescall/";
            mSOAPClientSAAJ = new SOAPClientSAAJ();

            System.out.println("before calling callSoapWebService");
            soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, "", 0);
            System.out.println("after calling callSoapWebService");
            System.out.println("  Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();

            itr = soapResponse.getSOAPBody().getChildElements();

            List<String> applicationDetails = new ArrayList<String>();
            System.out.println("  before forming the output:");
            applicationDetails = ReadXMLApplicationCodes(itr);

                    //////////////////////////////////////////////////////////////////////////////////////////////////////
                    System.out.println("---------4-----");
                    query = "INSERT INTO moi.payment_transactions(application_id,service_type_id,amount,language,track_id,payment_url,last_updated,status,user_civil_id,pay_for_civil_id)"
                            + " values (  ?, ?, ?,?, ?, ?, ?, ?,?, ?)";
                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    // preparedStmt.setInt(1, 11);
                    preparedStmt.setString(1, applicationID);
                    preparedStmt.setString(2, serviceTypeID);
                    preparedStmt.setString(3, amount);
                    preparedStmt.setString(4, language);
                    //preparedStmt.setString(5, recieptURL);
                    //preparedStmt.setString(6, errorURL);
                    preparedStmt.setString(5, trackID);
                    /*preparedStmt.setString(8, Udf1);
                    preparedStmt.setString(9, Udf2);
                    preparedStmt.setString(10, Udf3);
                    preparedStmt.setString(11, Udf4);
                    preparedStmt.setString(12, Udf5);* /
                    preparedStmt.setString(6, result);
                    preparedStmt.setString(7, lastUpdated);
                    preparedStmt.setString(8, "Initialized");
                    preparedStmt.setString(9, userCivilID);
                    preparedStmt.setString(10, payForCivilID);

                    // execute the preparedstatement
                    preparedStmt.execute();

                    System.out.println("---------6-----");
                    query = "INSERT INTO moi.payment_log(application_id,service_type_id,amount,language,track_id,payment_url,log_date,status,user_civil_id,pay_for_civil_id)"
                            + " values (  ?, ?, ?,?, ?, ?, ?, ?,?, ?)";
                    preparedStmt = con.prepareStatement(query);
                    // preparedStmt.setInt(1, 11);
                    preparedStmt.setString(1, applicationID);
                    preparedStmt.setString(2, serviceTypeID);
                    preparedStmt.setString(3, amount);
                    preparedStmt.setString(4, language);
                    // preparedStmt.setString(5, recieptURL);
                    //preparedStmt.setString(6, errorURL);
                    preparedStmt.setString(5, trackID);
                    /* preparedStmt.setString(8, Udf1);
                    preparedStmt.setString(9, Udf2);
                    preparedStmt.setString(10, Udf3);
                    preparedStmt.setString(11, Udf4);
                    preparedStmt.setString(12, Udf5);* /
                    preparedStmt.setString(6, result);
                    preparedStmt.setString(7, lastUpdated);
                    preparedStmt.setString(8, "Initialized");
                    preparedStmt.setString(9, userCivilID);
                    preparedStmt.setString(10, payForCivilID);

                    // execute the preparedstatement
                    preparedStmt.execute();

                    System.out.println("---------6-----");
                    query = "INSERT INTO moi.payment_items(track_id,item_number,amount,key1,key2,key3,key4,key5,key6,key7,key8,key9,key10)"
                            + " values (  ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ?,?)";
                    preparedStmt = con.prepareStatement(query);
                    // preparedStmt.setInt(1, 11);
                    for (int rec = 0; rec < noOfItems; rec++) {

                        preparedStmt.setString(1, trackID);
                        preparedStmt.setString(2, requestData.getItemList()[rec].getItemNumber());
                        preparedStmt.setString(3, requestData.getItemList()[rec].getAmount());
                        preparedStmt.setString(4, requestData.getItemList()[rec].getItemDetails().getKey1());
                        preparedStmt.setString(5, requestData.getItemList()[rec].getItemDetails().getKey2());
                        preparedStmt.setString(6, requestData.getItemList()[rec].getItemDetails().getKey3());
                        preparedStmt.setString(7, requestData.getItemList()[rec].getItemDetails().getKey4());
                        preparedStmt.setString(8, requestData.getItemList()[rec].getItemDetails().getKey5());
                        preparedStmt.setString(9, requestData.getItemList()[rec].getItemDetails().getKey6());
                        preparedStmt.setString(10, requestData.getItemList()[rec].getItemDetails().getKey7());
                        preparedStmt.setString(11, requestData.getItemList()[rec].getItemDetails().getKey8());
                        preparedStmt.setString(12, requestData.getItemList()[rec].getItemDetails().getKey9());
                        preparedStmt.setString(13, requestData.getItemList()[rec].getItemDetails().getKey10());

                        // execute the preparedstatement
                        preparedStmt.execute();
                    }

                    con.close();

                } catch (Exception e) {
                    System.out.println(e);
                }*/
                        System.out.println("---------4-----Address : " + pipe.getWebAddress());
                        InitResponse initiatePaymentResponse = new InitResponse();
                        initiatePaymentResponse.setStatusCode(val.toString());
                        initiatePaymentResponse.setStatusMessage(resultMessage);

                        initiatePaymentResponse.setApplicationId(applicationID);
                        initiatePaymentResponse.setApplicationName(applicationName); // get the application name drom the table
                        initiatePaymentResponse.setUserCivilId(userCivilID);
                        initiatePaymentResponse.setPayForCivilId(payForCivilID);
                        initiatePaymentResponse.setServiceTypeId(serviceTypeID);
                        initiatePaymentResponse.setServiceNameEn(serviceTypeNameEn);
                        initiatePaymentResponse.setServiceNameAr(serviceTypeNameAr);

                        initiatePaymentResponse.setPaymentUrl(pipe.getWebAddress());

                        response.setContentType("application/json");
                        response.setCharacterEncoding("utf-8");
                        String jsonData = gson.toJson(initiatePaymentResponse);
                        PrintWriter out = response.getWriter();

                        out.println(jsonData);
                    }
                    //response.sendRedirect(pipe.getWebAddress()); //To redirect the web address.
                } else {// error and no payment url returned due to error in knet most probably
                    resultMessage = pipe.getError();
                    System.err.println("no payment url returned  : " + pipe.getError());
                    InitResponse initiatePaymentResponse = new InitResponse();
                    initiatePaymentResponse.setStatusCode("-1");
                    initiatePaymentResponse.setStatusMessage("no payment url returned  : " + pipe.getError());
                    initiatePaymentResponse.setApplicationId(applicationID);
                    initiatePaymentResponse.setApplicationName(applicationName); // get the application name drom the table
                    initiatePaymentResponse.setUserCivilId(userCivilID);
                    initiatePaymentResponse.setPayForCivilId(payForCivilID);
                    initiatePaymentResponse.setServiceTypeId(serviceTypeID);
                    initiatePaymentResponse.setServiceNameEn(serviceTypeNameEn);
                    initiatePaymentResponse.setServiceNameAr(serviceTypeNameAr);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    String jsonData = gson.toJson(initiatePaymentResponse);
                    PrintWriter out = response.getWriter();

                    out.println(jsonData);
                }
            }

            /**
             * End of Request Processing*
             */
        } catch (SOAPException ex) {
            //Logger.getLogger(InitiatePayment.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(InitiatePayment.class.getName() + ex.getMessage());
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
       
        
        try {
            /*this do get function called by jsp page that recieve the data from knet so input
             in the request for this function is trandata and error in case of error  */
            System.out.println("---------------------------returned from knet get--------------------------------");

            setTrackID(request.getParameter("trackid"));
            System.out.println("---------------------------returned from knet get----------------getTrackID----------------" + getTrackID());
            //setStatus(request.getParameter("ErrorText"));
            setPaymentID(request.getParameter("paymentid"));
            System.out.println("---------------------------returned from knet get---------------getPaymentID-----------------" + getPaymentID());
            setTransID(request.getParameter("tranid"));
            System.out.println("---------------------------returned from knet get----------------getTransID----------------" + getTransID());
            setRefID(request.getParameter("ref"));
            System.out.println("---------------------------returned from knet get-------------------setRefID-------------" + getRefID());
            setResult(request.getParameter("result"));
            System.out.println("---------------------------returned from knet get----------------setResult----------------" + getResult());

            setAuthCode(request.getParameter("auth"));
            System.out.println("---------------------------returned from knet get----------------------getAuthCode----------" + getAuthCode());
            //setServiceExecStatus (request.getParameter("ErrorText"));
            setErrorID(request.getParameter("Error"));
            System.out.println("---------------------------returned from knet get--------ErrorID------------------------" + getErrorID());
            setErrorText(request.getParameter("ErrorText"));
            System.out.println("---------------------------returned from knet get-------------errorText-------------------" + errorText);

            
            String soapEndpointUrl;
            String soapAction;
            SOAPClientSAAJ mSOAPClientSAAJ;
            SOAPMessage soapResponse;
            Iterator itr;
            soapEndpointUrl = fileParameters.getPaymentIp() + ":" + fileParameters.getPaymentPort() + "/PY006/UPDATE_PAYMENT_TRANSACTION";
            soapAction = "http://tempuri.org/UpdatePaymentTransactioncall/";
            mSOAPClientSAAJ = new SOAPClientSAAJ();
            String xmlString = formXMLTransaction("COMPLETED");
            System.out.println("---------2----xml to log data to db :" + xmlString);
            System.out.println("before calling callSoapWebService");
            soapResponse = mSOAPClientSAAJ.callSoapWebService(soapEndpointUrl, soapAction, xmlString, 3);
            System.out.println("after calling callSoapWebService");
            System.out.println("  Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();
            itr = soapResponse.getSOAPBody().getChildElements();
            System.out.println("  before forming the output:");
            String returnCode = getXMLResult(itr);
            if (returnCode.equals("1")) {
                //success
            } else {
                //fail
            }

            /* System.out.println("---------1-----");
             Class.forName("com.mysql.cj.jdbc.Driver");
             System.out.println("---------2-----");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moi", "root", "1234");
             System.out.println("---------3-----");
             
             //here sonoo is database name, root is username and password
             Statement stmt = con.createStatement();
             ////////////////////////////////////////to be added/////////////////////////////////////////////////////
             //update the transactions http://10.10.1.1:29081/PY006/UPDATE_PAYMENT_TRANSACTION
             
             //////////////////////////////////////////////////////////////////////////////////////////////////////
             System.out.println("---------4-----");
             String query = "INSERT INTO moi.payment_log(amount,track_id,log_date,status,payment_id,error_id,error_text,trans_id,reference_id,date,result,auth_code)"
             + " values ( ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ?,?,?,?)";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             // preparedStmt.setInt(1, 11);
             preparedStmt.setString(1, amt);
             preparedStmt.setString(2, trackID);
             /* preparedStmt.setString(3, udf1);
             preparedStmt.setString(4, udf2);
             preparedStmt.setString(5, udf3);
             preparedStmt.setString(6, udf4);
             preparedStmt.setString(7, udf5);* /
             preparedStmt.setString(3, logDate);
             preparedStmt.setString(4, "Processing");
             preparedStmt.setString(5, paymentID);
             preparedStmt.setString(6, mErrorID);
             preparedStmt.setString(7, mErrorText);
             preparedStmt.setString(8, transID);
             preparedStmt.setString(9, ref);
             preparedStmt.setString(10, mDate);
             preparedStmt.setString(11, mResult);
             preparedStmt.setString(12, authCode);
             
             // execute the preparedstatement
             preparedStmt.execute();
             
             // create the java mysql update preparedstatement
             query = "update payment_transactions set last_updated = ? ,status = ? ,payment_id = ? ,"
             + "trans_id = ?,reference_id=?,date=?,result=?,auth_code=? where track_id = ?";
             preparedStmt = con.prepareStatement(query);
             
             preparedStmt.setString(1, logDate);
             preparedStmt.setString(2, "Processing");
             preparedStmt.setString(3, paymentID);
             //preparedStmt.setString(4, mErrorID);
             //preparedStmt.setString(5, mErrorText);
             preparedStmt.setString(4, transID);
             preparedStmt.setString(5, ref);
             preparedStmt.setString(6, mDate);
             preparedStmt.setString(7, mResult);
             preparedStmt.setString(8, authCode);
             preparedStmt.setString(9, trackID);
             
             // execute the java preparedstatement
             preparedStmt.executeUpdate();
             con.close();*/
            //handle response depend on it CAPTURED NOTCAPTURED
            
            
            if(getErrorID().isEmpty()||getErrorID().equals("")||getErrorID()== null)
            {
            
            }else
            {
               // https://ISERVICES.MOI.GOV.KW:5310/KNETRESULT/RESULT.JSP
            }
            
            
        } catch (SOAPException ex) {
            // Logger.getLogger(InitiatePayment.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(InitiatePayment.class.getName() + ":" + ex.getMessage());
        }

    }

    public String getXMLResult(Iterator itr) {

        String returnMessageCode = "";
        Node node = (Node) itr.next();

        String FirstNodeName = node.getNodeName();
        System.out.println("---1---- :: node name:" + FirstNodeName);
        if (FirstNodeName.equals("ns2:RecordPaymentTransactioncallResponse") || FirstNodeName.equals("a:UpdatePaymentTransactioncallResponse")) {
            Node node2 = node.getFirstChild();
            String SecondNodeName = node2.getNodeName();
            System.out.println("---2---- :: node name:" + SecondNodeName);
            if (SecondNodeName.equals("RecordPaymentTransactionExport") || SecondNodeName.equals("UpdatePaymentTransactionExport")) {

                NodeList childNodes = node2.getChildNodes();
                int numberOfChilds = childNodes.getLength();
                // System.out.println("---3---- :: number of childs:" + numberOfChilds);//outputparameters  array ...5
                for (int child = 0; child < numberOfChilds; child++) {
                    System.out.println("---3---- :: child name " + childNodes.item(child).getNodeName());
                    switch (childNodes.item(child).getNodeName()) {
                        case "ExportReturnMessage":
                            // System.out.println("---6---- :: value of success is " + messageCode);
                            NodeList childNodes2 = childNodes.item(child).getChildNodes();
                            int numberOfChilds2 = childNodes2.getLength();
                            for (int child2 = 0; child2 < numberOfChilds2; child2++) {
                                // System.out.println("---3---- :: child name " + childNodes.item(child).getNodeName());
                                switch (childNodes2.item(child2).getNodeName()) {

                                    case "Code":
                                        returnMessageCode = childNodes2.item(child2).getTextContent();
                                        break;
                                    default:
                                        break;
                                }
                            }
                            break;
                        case "ExportWsPaymentTransaction":
                            childNodes2 = childNodes.item(child).getChildNodes();
                            numberOfChilds2 = childNodes2.getLength();
                            for (int child2 = 0; child2 < numberOfChilds2; child2++) {
                                // System.out.println("---3---- :: child name " + childNodes.item(child).getNodeName());
                                switch (childNodes2.item(child2).getNodeName()) {

                                    case "TrackId":
                                        setTrackID(childNodes2.item(child2).getTextContent());
                                        break;
                                    default:
                                        break;
                                }
                            }
                            break;

                    }
                }

            }

        }
        return returnMessageCode;

    }

    public List<String> ReadXMLApplicationCodes(Iterator itr) {

        String returnMessageCode = "";
        String loopApplicationId = "";
        String applicationName = "";
        String successUrl = "";
        String failureUrl = "";
        System.out.println("---3---- :: child name " + getApplicationID());
        List<String> applicationDetails = new ArrayList<String>();
        Node node = (Node) itr.next();

        String FirstNodeName = node.getNodeName();
        System.out.println("---1---- :: node name:" + FirstNodeName);
        if (FirstNodeName.equals("ns2:ListApplicationCodescallResponse")) {
            Node node2 = node.getFirstChild();
            String SecondNodeName = node2.getNodeName();
            System.out.println("---2---- :: node name:" + SecondNodeName);
            if (SecondNodeName.equals("ListApplicationCodesExport")) {

                NodeList childNodes = node2.getChildNodes();
                int numberOfChilds = childNodes.getLength();
                // System.out.println("---3---- :: number of childs:" + numberOfChilds);//outputparameters  array ...5
                for (int child = 0; child < numberOfChilds; child++) {
                    System.out.println("---3---- :: child name " + childNodes.item(child).getNodeName());
                    switch (childNodes.item(child).getNodeName()) {
                        case "ExportReturnMessage":
                            // System.out.println("---6---- :: value of success is " + messageCode);
                            NodeList childNodes2 = childNodes.item(child).getChildNodes();
                            int numberOfChilds2 = childNodes2.getLength();
                            for (int child2 = 0; child2 < numberOfChilds2; child2++) {
                                // System.out.println("---3---- :: child name " + childNodes.item(child).getNodeName());
                                switch (childNodes2.item(child2).getNodeName()) {

                                    case "Code":
                                        returnMessageCode = childNodes2.item(child2).getTextContent();
                                        break;
                                    default:
                                        break;
                                }
                            }
                            break;

                    }
                }
                if (returnMessageCode.equals("1")) {
                    System.out.println("---3---- :: number of childs:" + numberOfChilds);
                    for (int child = 0; child < numberOfChilds; child++) {
                        System.out.println("---3---- :: child name " + childNodes.item(child).getNodeName());
                        switch (childNodes.item(child).getNodeName()) {
                            case "ExportGroupApplicationCodes":
                                NodeList details = childNodes.item(child).getChildNodes();
                                int detailsLength = details.getLength();
                                for (int i = 0; i < detailsLength; i++) {
                                    Node firstNode2 = details.item(i).getFirstChild();
                                    String FirstNodeName2 = firstNode2.getNodeName();
                                    System.out.println("---5---- :: node name:" + FirstNodeName2);
                                    if (FirstNodeName2.equals("ExportGrpAppReceiptPage")) {

                                        NodeList childNodes2 = firstNode2.getChildNodes();
                                        int numberOfChilds2 = childNodes2.getLength();
                                        System.out.println("---3---- :: number of childs2:" + numberOfChilds2);//outputparameters  array ...5
                                        for (int child2 = 0; child2 < numberOfChilds2; child2++) {
                                            switch (childNodes2.item(child2).getNodeName()) {

                                                case "ApplicationId":
                                                    loopApplicationId = childNodes2.item(child2).getTextContent();
                                                    System.out.println("--1-loopApplicationId--------" + loopApplicationId);
                                                    break;

                                                case "ApplicationName":
                                                    applicationName = childNodes2.item(child2).getTextContent();
                                                    System.out.println("--1-applicationName--------" + applicationName);
                                                    break;
                                                case "SuccessUrl":
                                                    successUrl = childNodes2.item(child2).getTextContent();
                                                    System.out.println("--1-SuccessUrl--------" + successUrl);
                                                    break;
                                                case "FailureUrl":
                                                    failureUrl = childNodes2.item(child2).getTextContent();
                                                    System.out.println("--1-failureUrl--------" + failureUrl);
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        System.out.println("-check -getApplicationID()--------");
                                        System.out.println("-check -getApplicationID()--------" + getApplicationID());
                                        if (loopApplicationId.equals(getApplicationID())) {
                                            System.out.println("--FOUNDED--------");
                                            applicationDetails.add(applicationName);
                                            System.out.println("-2--applicationName--------" + applicationName);
                                            applicationDetails.add(successUrl);
                                            System.out.println("-2--successUrl--------" + successUrl);
                                            applicationDetails.add(failureUrl);
                                            System.out.println("-2--failureUrl--------" + failureUrl);
                                            break;
                                        }

                                    }
                                }
                        }
                    }

                } else {

                }
            }

        }
        return applicationDetails;
    }

    public List<String> ReadXMLServiceTypes(Iterator itr) {

        String returnMessageCode = "";
        String loopServiceId = "";
        String serviceTypeNameEn = "";
        String serviceTypeNameAr = "";
        String aliasName = "";
        String terminalID = "";
        System.out.println("---3---- :: child name " + getApplicationID());
        List<String> serviceTypeDetails = new ArrayList<String>();

        Node node = (Node) itr.next();
        String FirstNodeName = node.getNodeName();
        System.out.println("---1---- :: node name:" + FirstNodeName);
        if (FirstNodeName.equals("ns2:ListPaymentServiceTypescallResponse")) {
            Node node2 = node.getFirstChild();
            String SecondNodeName = node2.getNodeName();
            System.out.println("---2---- :: node name:" + SecondNodeName);
            if (SecondNodeName.equals("ListPaymentServiceTypesExport")) {

                NodeList childNodes = node2.getChildNodes();
                int numberOfChilds = childNodes.getLength();
                // System.out.println("---3---- :: number of childs:" + numberOfChilds);//outputparameters  array ...5
                for (int child = 0; child < numberOfChilds; child++) {
                    System.out.println("---3---- :: child name " + childNodes.item(child).getNodeName());
                    switch (childNodes.item(child).getNodeName()) {
                        case "ExportReturnMessage":
                            // System.out.println("---6---- :: value of success is " + messageCode);
                            NodeList childNodes2 = childNodes.item(child).getChildNodes();
                            int numberOfChilds2 = childNodes2.getLength();
                            for (int child2 = 0; child2 < numberOfChilds2; child2++) {
                                // System.out.println("---3---- :: child name " + childNodes.item(child).getNodeName());
                                switch (childNodes2.item(child2).getNodeName()) {

                                    case "Code":
                                        returnMessageCode = childNodes2.item(child2).getTextContent();
                                        break;
                                    default:
                                        break;
                                }
                            }
                            break;

                    }
                }
                if (returnMessageCode.equals("1")) {
                    System.out.println("---3---- :: number of childs:" + numberOfChilds);
                    for (int child = 0; child < numberOfChilds; child++) {
                        System.out.println("---3---- :: child name " + childNodes.item(child).getNodeName());
                        switch (childNodes.item(child).getNodeName()) {
                            case "ExportGroupServiceTypes":
                                NodeList details = childNodes.item(child).getChildNodes();
                                int detailsLength = details.getLength();
                                for (int i = 0; i < detailsLength; i++) {
                                    Node firstNode2 = details.item(i).getFirstChild();
                                    String FirstNodeName2 = firstNode2.getNodeName();
                                    System.out.println("---5---- :: node name:" + FirstNodeName2);
                                    if (FirstNodeName2.equals("ExportGrpServiceTypes")) {

                                        NodeList childNodes2 = firstNode2.getChildNodes();
                                        int numberOfChilds2 = childNodes2.getLength();
                                        System.out.println("---3---- :: number of childs2:" + numberOfChilds2);//outputparameters  array ...5
                                        for (int child2 = 0; child2 < numberOfChilds2; child2++) {
                                            switch (childNodes2.item(child2).getNodeName()) {

                                                case "Id":
                                                    loopServiceId = childNodes2.item(child2).getTextContent();
                                                    System.out.println("--1-loopApplicationId--------" + loopServiceId);
                                                    break;

                                                case "NameAr":
                                                    serviceTypeNameAr = childNodes2.item(child2).getTextContent();
                                                    System.out.println("--1-serviceTypeNameAr--------" + serviceTypeNameAr);
                                                    break;
                                                case "NameEn":
                                                    serviceTypeNameEn = childNodes2.item(child2).getTextContent();
                                                    System.out.println("--1-serviceTypeNameEn--------" + serviceTypeNameEn);
                                                    break;
                                                case "AliasName":
                                                    aliasName = childNodes2.item(child2).getTextContent();
                                                    System.out.println("--1-aliasName--------" + aliasName);
                                                    break;
                                                case "TerminalId":
                                                    terminalID = childNodes2.item(child2).getTextContent();
                                                    System.out.println("--1-terminalID--------" + terminalID);
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }

                                        System.out.println("-check -getApplicationID()--------");
                                        System.out.println("-check -getApplicationID()--------" + getServiceTypeID());
                                        if (loopServiceId.equals(getServiceTypeID())) {
                                            System.out.println("--FOUNDED--------");
                                            serviceTypeDetails.add(serviceTypeNameAr);
                                            System.out.println("-2--serviceTypeNameAr--------" + serviceTypeNameAr);
                                            serviceTypeDetails.add(serviceTypeNameEn);
                                            System.out.println("-2--serviceTypeNameEn--------" + serviceTypeNameEn);
                                            serviceTypeDetails.add(aliasName);
                                            System.out.println("-2--aliasName--------" + aliasName);
                                            serviceTypeDetails.add(terminalID);
                                            System.out.println("-2--terminalID--------" + terminalID);
                                            break;
                                        }

                                    }
                                }
                        }
                    }

                } else {

                }
            }

        }
        return serviceTypeDetails;
    }

    public String formXMLTransaction(String status) {

        String xmlString = "";

        if (status.equals("INITIALIZED")) {
            xmlString = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:rec=\"http://tempuri.org/RecordPaymentTransaction/\">"
                    + "   <soapenv:Header/>"
                    + "   <soapenv:Body>";
            xmlString = xmlString + "<rec:RecordPaymentTransactioncall><RecordPaymentTransactionImport command=\"\" clientId=\"\" clientPassword=\"\" nextLocation=\"\" exitState=\"0\" dialect=\"\">"
                    + "<ImportWsPaymentTransaction><Status>" + status + "</Status><ApplicationId>" + getApplicationID() + "</ApplicationId><Language>EN</Language><UserCivilId>" + getUserCivilID() + "</UserCivilId>"
                    + "<PayForPersonNum>" + getPayForCivilID() + "</PayForPersonNum><ServiceTypeId>" + getServiceTypeID() + "</ServiceTypeId><TotalAmount>" + getAmount() + "</TotalAmount><PaymentUrl></PaymentUrl>"
                    + "<PaymentId></PaymentId><TransId></TransId><ReferenceId></ReferenceId><Result>" + "INITIALIZED" + "</Result><AuthCode></AuthCode><ServiceExecStatus></ServiceExecStatus>"
                    + "<Source>POS</Source></ImportWsPaymentTransaction><ImportGroupPaymentItems>";

            for (int i = 0; i < mItemList.size(); i++) {
                xmlString = xmlString + "<row><ImportGrpWsPaymentItem> <ReceiptNumber></ReceiptNumber> <Amount>" + mItemList.get(i).getAmount() + "</Amount>"
                        + "<Key1>" + mItemList.get(i).getItemDetails().getKey1() + "</Key1><Key2>" + mItemList.get(i).getItemDetails().getKey2() + "</Key2>"
                        + "<Key3>" + mItemList.get(i).getItemDetails().getKey3() + "</Key3><Key4>" + mItemList.get(i).getItemDetails().getKey4() + "</Key4>"
                        + "<Key5>" + mItemList.get(i).getItemDetails().getKey5() + "</Key5><Key6>" + mItemList.get(i).getItemDetails().getKey6() + "</Key6>"
                        + "<Key7>" + mItemList.get(i).getItemDetails().getKey7() + "</Key7><Key8>" + mItemList.get(i).getItemDetails().getKey8() + "</Key8>"
                        + "<Key9>" + mItemList.get(i).getItemDetails().getKey9() + "</Key9><Key10>" + mItemList.get(i).getItemDetails().getKey10() + "</Key10>"
                        + "</ImportGrpWsPaymentItem></row>";
            }

            xmlString = xmlString + " </ImportGroupPaymentItems></RecordPaymentTransactionImport></rec:RecordPaymentTransactioncall></soapenv:Body></soapenv:Envelope>";
        } else {
            xmlString = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:upd=\"http://tempuri.org/UpdatePaymentTransaction/\">"
                    + "   <soapenv:Header/>"
                    + "   <soapenv:Body>";
            xmlString = xmlString + "<upd:UpdatePaymentTransactioncall><UpdatePaymentTransactionImport command=\"?\" clientId=\"?\" clientPassword=\"?\" nextLocation=\"?\" exitState=\"0\" dialect=\"?\">\n"
                    + "<ImportWsPaymentTransaction><TrackId>" + getTrackID() + "</TrackId><Status>" + status + "</Status>";

            if (status.equals("PROCESSING")) {
                xmlString = xmlString + "<PaymentId>" + getPaymentID() + "</PaymentId><PaymentUrl>" + getPaymentURL().replace("&", "&amp;") + "</PaymentUrl>";
            }

            xmlString = xmlString + "<TransId>" + getTransID() + "</TransId><ReferenceId>" + getRefID() + "</ReferenceId><Result>" + getResult() + "</Result><AuthCode>" + getAuthCode() + "</AuthCode>"
                    + "<ErrorId>" + getErrorID() + "</ErrorId><ErrorText>" + getErrorText() + "</ErrorText></ImportWsPaymentTransaction></UpdatePaymentTransactionImport></upd:UpdatePaymentTransactioncall>"
                    + "</soapenv:Body></soapenv:Envelope>";

        }

        return xmlString;
    }
}
