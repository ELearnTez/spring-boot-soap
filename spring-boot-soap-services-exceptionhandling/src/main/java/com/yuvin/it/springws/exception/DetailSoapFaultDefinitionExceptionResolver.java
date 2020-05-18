package com.yuvin.it.springws.exception;

import javax.xml.namespace.QName;

import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

public class DetailSoapFaultDefinitionExceptionResolver extends SoapFaultMappingExceptionResolver {

	private static final QName CODE = new QName("statusCode");
	private static final QName MESSAGE = new QName("message");

	@Override
	protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
		
		if (ex instanceof ServiceFaultException) {
			ServiceFault status = ((ServiceFaultException) ex).getError();
			SoapFaultDetail detail = fault.addFaultDetail();
			detail.addFaultDetailElement(CODE).addText(status.getCode());
			detail.addFaultDetailElement(MESSAGE).addText(status.getDescription());
		}
	}

}