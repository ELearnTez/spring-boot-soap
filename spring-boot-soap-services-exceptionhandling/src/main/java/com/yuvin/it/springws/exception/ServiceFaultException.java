package com.yuvin.it.springws.exception;


public class ServiceFaultException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ServiceFault error;

	public ServiceFaultException(String message, ServiceFault error) {
		super(message);
		this.error = error;
	}

	public ServiceFaultException(String message, Throwable e, ServiceFault error) {
		super(message, e);
		this.error = error;
	}

	public ServiceFault getError() {
		return error;
	}

	public void setError(ServiceFault error) {
		this.error = error;
	}

}