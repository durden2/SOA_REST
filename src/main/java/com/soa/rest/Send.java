package com.soa.rest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.commons.codec.binary.Base64;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import javax.ws.rs.core.HttpHeaders;

public class Send {

    public static void main(String[] args) {

        try {

            ClientRequest request = new ClientRequest(
                    "http://localhost:8080/soa/rest/consume/posttext");
            request.accept("application/json");

            String input = "{\"qty\":100,\"name\":\"iPad 4\"}";
            request.body("application/json", input);

            ClientResponse<String> response = request.post(String.class);

            if (response.getStatus() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new ByteArrayInputStream(response.getEntity().getBytes())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}