/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moi;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.xml.soap.*;

/**
 *
 * @author MOI
 */
public class SOAPClientSAAJ {

    /**
     * @return the stepNo
     */
    public int getStepNo() {
        return stepNo;
    }

    /**
     * @param stepNo the stepNo to set
     */
    public void setStepNo(int stepNo) {
        this.stepNo = stepNo;
    }

    
   private int stepNo = -1;

    public void createSoapEnvelopeGetApplicationDetails(SOAPMessage soapMessage) throws SOAPException {
        //System.out.println("-----------------------------7---------createSoapEnvelopeGet--------------");
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "lis";
        String myNamespaceURI = "http://tempuri.org/ListApplicationCodes/";

        // SOAP Envelope
        //System.out.println("-----------------------------8-----------------------");
        SOAPEnvelope envelope = soapPart.getEnvelope();
        //System.out.println("-----------------------------9-----------------------");
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
        //System.out.println("-----------------------------10-----------------------");

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        //System.out.println("-----------------------------11-----------------------");
        SOAPElement soapBodyElem = soapBody.addChildElement("ListApplicationCodescall", myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("ListApplicationCodesImport");
       
    }
    
            
     public void createSoapEnvelopeGetServiceDetails(SOAPMessage soapMessage) throws SOAPException {
        //System.out.println("-----------------------------7---------createSoapEnvelopeGet--------------");
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "lis";
        String myNamespaceURI = "http://tempuri.org/ListPaymentServiceTypes/";

        // SOAP Envelope
        //System.out.println("-----------------------------8-----------------------");
        SOAPEnvelope envelope = soapPart.getEnvelope();
        //System.out.println("-----------------------------9-----------------------");
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
        //System.out.println("-----------------------------10-----------------------");

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        //System.out.println("-----------------------------11-----------------------");
        SOAPElement soapBodyElem = soapBody.addChildElement("ListPaymentServiceTypescall", myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("ListPaymentServiceTypesImport");
       
    }

    public void createSoapEnvelopeSaveTransaction(SOAPMessage soapMessage) throws SOAPException {
          SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "";
        String myNamespaceURI = "";
        if(getStepNo()== 2)
        {
             myNamespace = "rec";
         myNamespaceURI = "http://tempuri.org/RecordPaymentTransaction/";
            
        }else
        {
             myNamespace = "upd";
         myNamespaceURI = "http://tempuri.org/UpdatePaymentTransaction/";
        }

        // SOAP Envelope
        System.out.println("-----------------------------8-----------------------");
        SOAPEnvelope envelope = soapPart.getEnvelope();
        //System.out.println("-----------------------------9-----------------------");
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
        //System.out.println("-----------------------------10-----------------------");

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem ;
        SOAPElement soapBodyElem1;
        if(getStepNo()== 2)
        {
        //System.out.println("-----------------------------11-----------------------");
         soapBodyElem = soapBody.addChildElement("RecordPaymentTransactioncall", myNamespace);
        
         soapBodyElem1 = soapBodyElem.addChildElement("RecordPaymentTransactionImport");
        }else
        {
            soapBodyElem = soapBody.addChildElement("UpdatePaymentTransactioncall", myNamespace);
        
         soapBodyElem1 = soapBodyElem.addChildElement("UpdatePaymentTransactionImport");
        }
       
        SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("ImportWsPaymentTransaction");
       
        //System.out.println("-----------------------------16-----------------------");
        SOAPElement soapBodyElem3 = soapBodyElem2.addChildElement("Status");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem3.addTextNode("1");
        SOAPElement soapBodyElem4 = soapBodyElem2.addChildElement("ApplicationId");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem4.addTextNode("00000000");
        SOAPElement soapBodyElem5 = soapBodyElem2.addChildElement("Language");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem5.addTextNode("00000000");
        SOAPElement soapBodyElem6 = soapBodyElem2.addChildElement("UserCivilId");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem6.addTextNode("00000000");
        SOAPElement soapBodyElem7 = soapBodyElem2.addChildElement("PayForPersonNum");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem7.addTextNode("00000000");
        SOAPElement soapBodyElem8 = soapBodyElem2.addChildElement("ServiceTypeId");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem8.addTextNode("00000000");
        SOAPElement soapBodyElem9 = soapBodyElem2.addChildElement("TotalAmount");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem9.addTextNode("00000000");
        SOAPElement soapBodyElem10 = soapBodyElem2.addChildElement("PaymentUrl");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem10.addTextNode("00000000");
        SOAPElement soapBodyElem11 = soapBodyElem2.addChildElement("PaymentId");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem11.addTextNode("00000000");
        SOAPElement soapBodyElem12 = soapBodyElem2.addChildElement("TransId");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem12.addTextNode("00000000");
        SOAPElement soapBodyElem13 = soapBodyElem2.addChildElement("ReferenceId");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem13.addTextNode("00000000");
        SOAPElement soapBodyElem14 = soapBodyElem2.addChildElement("Result");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem14.addTextNode("00000000");
        SOAPElement soapBodyElem15 = soapBodyElem2.addChildElement("AuthCode");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem15.addTextNode("00000000");
        SOAPElement soapBodyElem16 = soapBodyElem2.addChildElement("ServiceExecStatus");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem16.addTextNode("00000000");
        SOAPElement soapBodyElem17 = soapBodyElem2.addChildElement("Source");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem17.addTextNode("00000000");
        
        SOAPElement soapBodyElem18 = soapBodyElem1.addChildElement("ImportGroupPaymentItems");
        
        
        
         
    }
    public void createSoapEnvelopePost(SOAPMessage soapMessage) throws SOAPException {
        //System.out.println("-----------------------------7----------------createSoapEnvelopePost-------");
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "def";
        String myNamespaceURI = "http://www.moi.gov.kw/Services/MOIResidencyRenewService/V1/Definitions";

        // SOAP Envelope
        System.out.println("-----------------------------8-----------------------");
        SOAPEnvelope envelope = soapPart.getEnvelope();
        //System.out.println("-----------------------------9-----------------------");
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
        //System.out.println("-----------------------------10-----------------------");

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        //System.out.println("-----------------------------11-----------------------");
        SOAPElement soapBodyElem = soapBody.addChildElement("ValidateResidencyRenewal", myNamespace);
        //System.out.println("-----------------------------12-----------------------");

        //System.out.println("-----------------------------13-----------------------");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("civilId");
        soapBodyElem1.addTextNode("");
        //System.out.println("-----------------------------14-----------------------");
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("residencyPeriod");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem2.addTextNode("");
        //System.out.println("-----------------------------16-----------------------");
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("residencyPeriodUnit");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem3.addTextNode("1");
        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("phoneNumber");
        //System.out.println("-----------------------------15-----------------------");
        soapBodyElem4.addTextNode("00000000");

    }
       

    public SOAPMessage callSoapWebService(String soapEndpointUrl, String soapAction, String xmlString, int stepNo) {
        SOAPMessage soapResponse = null;
        try {
            // Create SOAP Connection
           
           
            System.out.println("---------beginning of sending soap request2.1---------------");
            setStepNo(stepNo);
           /* if(requestDataArray!= null)
            {
            for (PostRequestStep1 requestDataArray1 : requestDataArray) {
                System.out.println("---------beginning of sending soap request2.2---------------");
                if (requestDataArray1.getKey().equals("ResidentCivilId")) {
                    //System.out.println("---------beginning of sending soap request2.22222---------------");
                    setResidentCivilId(requestDataArray1.getValue());
                }
                if (requestDataArray1.getKey().equals("FeesPeriod")) {
                    //System.out.println("---------beginning of sending soap request2.33333333---------------");
                    setFeesPeriodS(requestDataArray1.getValue());
                    //System.out.println("---------beginning of sending soap request2.44444444-------------:--" + requestDataArray1.getValue());
                   
                    //System.out.println("---------beginning of sending soap request2.66666666---------------");
                }
                if (requestDataArray1.getKey().equals("UserCategory")) {
                    //System.out.println("---------beginning of sending soap request2.22222---------------");
                    setUserCategory(requestDataArray1.getValue());
                    canCalcFees = 1;
                }

            }
             System.out.println("---------beginning of sending soap request55--------------");
            int resFees = 0;
           
            
                    System.out.println("---------beginning of sending soap request2.5555555---------------");
            //setResdiencyFees(String.valueOf(resFees));
            
            }*/
            //System.out.println("---------beginning of sending soap request2.3---------------");
            //System.out.println("---------beginning of sending soap request 2---------------");
          
            //System.out.println("---------beginning of sending soap request 3---------------");
          /*  if (requestDataArray != null) {
                setRequestDataArray(requestDataArray);

            }*/
            //System.out.println("---------beginning of sending soap request 4---------------");
            //System.out.println("---------beginning of sending soap request 4---------------");
            //System.out.println("-----------------------------1-----------------------");
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            //System.out.println("-----------------------------2-----------------------");
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            //System.out.println("-----------------------------3-----------------------");
            // Send SOAP Message to SOAP Server
            //System.out.println("---------beginning of sending soap request 5---------------");
            if(stepNo==2||stepNo==3)
            {
            InputStream is = new ByteArrayInputStream(xmlString.getBytes());
            SOAPMessage tempRequest = MessageFactory.newInstance().createMessage(null, is);
            soapResponse = soapConnection.call(tempRequest, soapEndpointUrl);
            }else
            {
            soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);
            }
            soapConnection.close();

        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
        }
        return soapResponse;
    }

    private SOAPMessage createSOAPRequest(String soapAction) throws Exception {
        System.out.println("-----------------------------4-----------------------");
        MessageFactory messageFactory = MessageFactory.newInstance();
        // System.out.println("-----------------------------5-----------------------");
        SOAPMessage soapMessage = messageFactory.createMessage();
        //System.out.println("-----------------------------6-----------------------");
        switch (getStepNo()) {
            case 0:
                //System.out.println("-------------------------createSoapEnvelopeGet----------------------");
                createSoapEnvelopeGetApplicationDetails(soapMessage);
                break;
                 case 1:
                //System.out.println("-------------------------createSoapEnvelopeGet----------------------");
                createSoapEnvelopeGetServiceDetails(soapMessage);
                break;
            case 2:

                createSoapEnvelopeSaveTransaction(soapMessage);
                break;
          
            default:
                break;
        }

        //System.out.println("-----------------------------17-----------------------");
        MimeHeaders headers = soapMessage.getMimeHeaders();
        //System.out.println("-----------------------------18-----------------------");
        headers.addHeader("SOAPAction", soapAction);
        //System.out.println("-----------------------------19-----------------------");

        soapMessage.saveChanges();
        //System.out.println("-----------------------------20-----------------------");
        /* Print the request message, just for debugging purposes */
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println("\n");

        return soapMessage;
    }

  
}
