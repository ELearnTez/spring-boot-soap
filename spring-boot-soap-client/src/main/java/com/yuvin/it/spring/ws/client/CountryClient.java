package com.yuvin.it.spring.ws.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.yuvin.it.consumingwebservice.wsdl.GetCountryRequest;
import com.yuvin.it.consumingwebservice.wsdl.GetCountryResponse;

public class CountryClient{

  private static final Logger log = LoggerFactory.getLogger(CountryClient.class);
  
  
  @Autowired
  private WebServiceTemplate webServiceTemplate;

  public GetCountryResponse getCountry(String country) {

    GetCountryRequest request = new GetCountryRequest();
    request.setName(country);

    log.info("Requesting location for " + country);

    GetCountryResponse response = (GetCountryResponse)
    		webServiceTemplate
    		.marshalSendAndReceive("http://localhost:8080/ws/countries", 
    								request,
						            new SoapActionCallback(
						                "http://spring.io/guides/gs-producing-web-service/GetCountryRequest")
			);

    return response;
  }

}