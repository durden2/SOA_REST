package com.soa.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Gandi on 06.06.2016.
 */
public class Login {

    public static void main (String[] args) {

        String BASE_URL = "http://localhost:8080/soa/rest/produce/getxml";

        DefaultHttpClient client = new DefaultHttpClient();

        client.getCredentialsProvider().setCredentials(
                new AuthScope("localhost", 8080),
                new UsernamePasswordCredentials("fafa", "pass"));

        HttpGet httppost = new HttpGet(BASE_URL);

        System.out.println("executing request " + httppost.getRequestLine());
        org.apache.http.HttpResponse response = null;
        try {
            response = client.execute(httppost);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            client.getConnectionManager().shutdown();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
