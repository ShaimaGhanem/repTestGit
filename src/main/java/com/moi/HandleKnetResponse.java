

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.moi;

import com.fss.plugin.iPayPipe;
import static com.moi.InitiatePayment.fileParameters;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@WebServlet("/HandleResponse")
public class HandleKnetResponse extends HttpServlet {

  String paymentID = "", mResult = "", mDate = "", ref = "", trackID = "", authCode = "";
            String transID = "", amt = "", udf1 = "", udf2 = "", udf3 = "", udf4 = "", udf5 = "", mErrorID = "", mErrorText = "";
            String successURL="",failureURL = "";
            String applicationID = "";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
        try {
            System.out.println("---------------returned---hello do post handle request-----------------------------");
            /*this do get function called by jsp page that recieve the data from knet so input
            in the request for this function is trandata and error in case of error  */
            System.out.println("---------------------------returned from knet get--------------------------------");
            iPayPipe pipe = new iPayPipe();
            Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
            String filePath = path.toString() + "//ResourceFiles";
            //Initialization
            
            String aliasName = "moi"; //Terminal Alias Name
            //Set Values
            pipe.setResourcePath(filePath);
            pipe.setKeystorePath(filePath);
            pipe.setAlias(aliasName);
            String errorText = request.getParameter("ErrorText");
            System.out.println("---------------------------returned from knet errorText--------------------------------" + errorText);
            int result = pipe.parseEncryptedRequest(request.getParameter("trandata"));
            System.out.println("---------------------------returned from knet trandata--------------------------------" + result);
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String logDate = dtf.format(now);
            if (result != 0) { // request fail
                pipe.getError();
                System.out.println("---------------------------fail--------------------------------" + pipe.getError());
            } else {
                if (errorText == null) {  //sh:result is 0 success and no error text
                    mResult = pipe.getResult();
                    System.out.println("----------------------c-----returned from knet mResult--------------------------------" + mResult);
                    mDate = pipe.getDate();
                    
                    System.out.println("----------------------c-----returned from knet mDate--------------------------------" + mDate);
                    ref = pipe.getRef();
                    System.out.println("-----------------------c----returned from knet ref--------------------------------" + ref);
                    trackID = pipe.getTrackId();
                    System.out.println("----------------------c-----returned from knet trackID--------------------------------" + trackID);
                    transID = pipe.getTransId();
                    System.out.println("----------------------c-----returned from knet transID--------------------------------" + transID);
                    amt = pipe.getAmt();
                    System.out.println("----------------------c-----returned from knet amt--------------------------------" + amt);
                    authCode = pipe.getAuth();
                    System.out.println("----------------------c-----returned from knet auth--------------------------------" + authCode);
                    udf1 = pipe.getUdf1();
                    System.out.println("-----------------------c----returned from knet udf5--------------------------------" + udf1);
                    udf2 = pipe.getUdf2();
                    System.out.println("-----------------------c----returned from knet udf5--------------------------------" + udf2);
                    udf3 = pipe.getUdf3();
                    System.out.println("-----------------------c----returned from knet udf5--------------------------------" + udf3);
                    udf4 = pipe.getUdf4();
                    System.out.println("----------------------c-----returned from knet udf5--------------------------------" + udf4);
                    udf5 = pipe.getUdf5();
                    System.out.println("---------------------c------returned from knet udf5--------------------------------" + udf5);
                    paymentID = pipe.getPaymentId();
                    System.out.println("---------------------c------returned from knet paymentID--------------------------------" + paymentID);
                } else { //sh:result is success and there is error text
                    mErrorText = request.getParameter("ErrorText");
                    System.out.println("----------------------n-----returned from knet mErrorText--------------------------------" + mErrorText);
                    paymentID = request.getParameter("paymentid");
                    System.out.println("----------------------n-----returned from knet paymentid--------------------------------" + paymentID);
                    mErrorID = request.getParameter("Error");
                    System.out.println("----------------------n-----returned from knet Error--------------------------------" + mErrorID);
                    trackID = request.getParameter("trackid");
                    System.out.println("---------------------n------returned from knet trackid--------------------------------" + trackID);
                    amt = request.getParameter("amt");
                    System.out.println("----------------------n-----returned from knet amt--------------------------------" + amt);
                    successURL = request.getParameter("udf1");
                    System.out.println("----------------------n-----returned from knet udf5--------------------------------" + udf1);
                    failureURL = request.getParameter("udf2");
                    System.out.println("----------------------n-----returned from knet udf5--------------------------------" + udf2);
                    udf3 = request.getParameter("udf3");
                    System.out.println("----------------------n-----returned from knet udf5--------------------------------" + udf3);
                    udf4 = request.getParameter("udf4");
                    System.out.println("----------------------n-----returned from knet udf5--------------------------------" + udf4);
                    udf5 = request.getParameter("udf5");
                    System.out.println("----------------------n-----returned from knet udf5--------------------------------" + udf5);
                    authCode = request.getParameter("auth");
                }
            }
            /**
             * End of Response Processing*
             */
           /* if(mResult.equals("NOT+CAPTURED"))
            {
                mResult = "NOT CAPTURED";
            }*/
            
            
            applicationID = udf1;
            
            //save result
            //handle response depend on it CAPTURED NOTCAPTURED
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
            
            String recieptURL = applicationDetails.get(1);
            if (recieptURL.isEmpty()) {
                recieptURL = fileParameters.getDefaultRecieptURL();
            }
            String errorURL = applicationDetails.get(2);
            if (errorURL.isEmpty()) {
                errorURL = fileParameters.getDefaultErrorURL();
            }
            
            switch (mResult){
                case "CAPTURED":
                   //successURL  https://esptest.moi.gov.kw/KnetResult/reciept.jsp
                    response.sendRedirect(recieptURL+"?auth="+authCode+"&postdat="+mDate+"&ref="+ref+"&trackid="+trackID+"&amt="+amt+"&paymentid="+paymentID+"&tranid="+transID+"&result="+mResult+"&udf1="+udf1+"&udf2="+udf2+"&udf3="+udf3+"&udf4="+udf4+"&udf5="+udf5);
                    
                    break;
                    
                    case "NOT+CAPTURED":
                    //failureURL "https://esptest.moi.gov.kw/KnetResult/error.jsp
                    response.sendRedirect(errorURL+"?auth="+authCode+"&postdat="+mDate+"&ref="+ref+"&trackid="+trackID+"&amt="+amt+"&paymentid="+paymentID+"&tranid="+transID+"&result="+mResult+"&udf1="+udf1+"&udf2="+udf2+"&udf3="+udf3+"&udf4="+udf4+"&udf5="+udf5);
                     break;
                     
                
                    case "CANCELED":
                     response.sendRedirect(errorURL+"?auth="+authCode+"&postdat="+mDate+"&ref="+ref+"&trackid="+trackID+"&amt="+amt+"&paymentid="+paymentID+"&tranid="+transID+"&result="+mResult+"&udf1="+udf1+"&udf2="+udf2+"&udf3="+udf3+"&udf4="+udf4+"&udf5="+udf5);
                   break;
                
                   
                default:
                    break;
               
            }
            
            
            
            
        } catch (SOAPException ex) {
            Logger.getLogger(HandleKnetResponse.class.getName()).log(Level.SEVERE, null, ex);
        }

 
            

          
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("------------------hello do get handle request-----------------------------");

    }
    
     public List<String> ReadXMLApplicationCodes(Iterator itr) {

        String returnMessageCode = "";
        String loopApplicationId = "";
        String applicationName = "";
        String successUrl = "";
        String failureUrl = "";
        System.out.println("---3---- :: child name " + applicationID);
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
                                        System.out.println("-check -getApplicationID()--------" + applicationID);
                                        if (loopApplicationId.equals(applicationID)) {
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
                      

                    }
                }

            }

        }
        return returnMessageCode;

    }
    
     public String formXMLTransaction(String status) {

        String xmlString = "";

     
            xmlString = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:upd=\"http://tempuri.org/UpdatePaymentTransaction/\">"
                    + "   <soapenv:Header/>"
                    + "   <soapenv:Body>";
            xmlString = xmlString + "<upd:UpdatePaymentTransactioncall><UpdatePaymentTransactionImport command=\"?\" clientId=\"?\" clientPassword=\"?\" nextLocation=\"?\" exitState=\"0\" dialect=\"?\">\n"
                    + "<ImportWsPaymentTransaction><TrackId>" + trackID + "</TrackId><Status>" + status + "</Status>";
            xmlString = xmlString + "<TransId>" + transID + "</TransId><ReferenceId>" + ref + "</ReferenceId><PaymentId>"+paymentID+"</PaymentId><Result>" + mResult + "</Result><AuthCode>" + authCode + "</AuthCode>"
                    + "<ErrorId>" + mErrorID + "</ErrorId><ErrorText>" + mErrorText + "</ErrorText></ImportWsPaymentTransaction></UpdatePaymentTransactionImport></upd:UpdatePaymentTransactioncall>"
                    + "</soapenv:Body></soapenv:Envelope>";

       

        return xmlString;
    }


    }

   