/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moi;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author MOI
 */
public class FileParameters {

    /**
     * @return the resourcePath
     */
    public String getResourcePath() {
        return resourcePath;
    }

    /**
     * @param resourcePath the resourcePath to set
     */
    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    /**
     * @return the keystorePath
     */
    public String getKeystorePath() {
        return keystorePath;
    }

    /**
     * @param keystorePath the keystorePath to set
     */
    public void setKeystorePath(String keystorePath) {
        this.keystorePath = keystorePath;
    }

    /**
     * @return the defaultRecieptURL
     */
    public String getDefaultRecieptURL() {
        return defaultRecieptURL;
    }

    /**
     * @param defaultRecieptURL the defaultRecieptURL to set
     */
    public void setDefaultRecieptURL(String defaultRecieptURL) {
        this.defaultRecieptURL = defaultRecieptURL;
    }

    /**
     * @return the defaultErrorURL
     */
    public String getDefaultErrorURL() {
        return defaultErrorURL;
    }

    /**
     * @param defaultErrorURL the defaultErrorURL to set
     */
    public void setDefaultErrorURL(String defaultErrorURL) {
        this.defaultErrorURL = defaultErrorURL;
    }

    /**
     * @return the paymentIp
     */
    public String getPaymentIp() {
        return paymentIp;
    }

    /**
     * @param paymentIp the paymentIp to set
     */
    public void setPaymentIp(String paymentIp) {
        this.paymentIp = paymentIp;
    }

    /**
     * @return the paymentPort
     */
    public String getPaymentPort() {
        return paymentPort;
    }

    /**
     * @param paymentPort the paymentPort to set
     */
    public void setPaymentPort(String paymentPort) {
        this.paymentPort = paymentPort;
    }

    private String paymentIp = "";
    private String paymentPort = "";
    private String defaultRecieptURL = "";
    private String defaultErrorURL = "";
    private String resourcePath = "";
    private String keystorePath = "";

    public void readParameters() throws IOException {

        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        String filePath = path.toString() + "//AppVariables//";
        // System.out.println("-----1------------" + path.toString());
       // String filePath = "//shared//lzcollect//members//SharedResources//AppVariables//";
        //System.out.println("-----1----filePath--------" + filePath);

        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get(filePath + "NewPaymentParameters.json"));

        envParam[] envParamData = gson.fromJson(reader, envParam[].class);
        for (int i = 0; i < envParamData.length; i++) {
            //System.out.println("-----66------------" + envParamData[i].getKey());
            switch (envParamData[i].getKey()) {
                case "paymentIp":
                    setPaymentIp(envParamData[i].getValue());
                    break;
                case "paymentPort":
                    setPaymentPort(envParamData[i].getValue());
                    break;
                case "defaultRecieptURL":
                    setDefaultRecieptURL(envParamData[i].getValue());
                    break;
                case "defaultErrorURL":
                    setDefaultErrorURL(envParamData[i].getValue());
                    break;   
                case "resourcePath":
                    setResourcePath(envParamData[i].getValue());
                    break;
                case "keystorePath":
                    setKeystorePath(envParamData[i].getValue());
                    break;   
                default:
                    break;

            }

        }
        reader.close();
    }

}
