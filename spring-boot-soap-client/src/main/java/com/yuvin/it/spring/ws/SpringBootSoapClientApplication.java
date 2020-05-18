package com.yuvin.it.spring.ws;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.yuvin.it.consumingwebservice.wsdl.GetCountryResponse;
import com.yuvin.it.spring.ws.client.CountryClient;

@SpringBootApplication
public class SpringBootSoapClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSoapClientApplication.class, args);
	}
	
	

	  @Bean
	 public CommandLineRunner lookup(CountryClient quoteClient) {
	    return args -> {
	      String country = "Poland";

	      if (args.length > 0) {
	        country = args[0];
	      }
	      GetCountryResponse response = quoteClient.getCountry(country);
	      
	      System.err.println(response.getCountry().getCurrency());
	      System.err.println(response.getCountry().getName());
	      System.err.println(response.getCountry().getCapital());
	    };
	  }
	  
	  @Bean	  
	  public CountryClient countryClient() {
		  return new CountryClient();
	  }
	  
	  @Bean
	  public WebServiceTemplate webServiceTemplate() {
		  return new WebServiceTemplate(jaxb2Marshaller() );
	  }
	  
	  @Bean
	  public Jaxb2Marshaller jaxb2Marshaller() {
		  Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		  marshaller.setPackagesToScan("com.yuvin.it.consumingwebservice.wsdl");
		 return marshaller; 
	  }

}
