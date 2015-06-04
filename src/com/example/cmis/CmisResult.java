package com.example.cmis;

public class CmisResult<T> {
private Exception exception;
private T data ;

public CmisResult(Exception exception,T data){
	super();
	this.exception = exception;
	this.data = data;
	
}

public boolean hasException(){
	return (exception!=null);
}

public Exception getException(){
	return exception;
}
public T getDaTa(){
	return data;
}




}
