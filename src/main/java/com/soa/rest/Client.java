package com.soa.rest;

import org.jboss.resteasy.util.Base64;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Gandi on 05.06.2016.
 */
public class Client {
    public static void main(String[] args) {

        try {
            // URL url = new URL ("http://ip:port/download_url");
            URL url = new URL("http://localhost:8080/soa");
            String authStr = "fafa:pass";
            String authEncoded = Base64.encodeBytes(authStr.getBytes());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + authEncoded);
            connection.connect();

            int code = connection.getResponseCode();
            if (code == 200){
                System.out.print("Success!");
            }if(code == 401)
                System.out.print("Wrong creditals!");
        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
