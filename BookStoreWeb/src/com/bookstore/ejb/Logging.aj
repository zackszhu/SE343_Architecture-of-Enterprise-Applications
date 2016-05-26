package com.bookstore.ejb;

public aspect Logging {
	pointcut log() : 
		call(public * *Remote.*(..));
	
	before() : log(){  
		System.out.printf("Enters on method: %s. \n", thisJoinPoint.getSignature());
		Object[] arguments = thisJoinPoint.getArgs();
	    for (int i =0; i < arguments.length; i++){
	        Object argument = arguments[i];
	        if (argument != null){
	            System.out.printf("With argument of type %s and value %s. \n", argument.getClass().toString(), argument);
	        }
	    }
	}

	after() returning() : log() {
		System.out.printf("Exits on method: %s. \n", thisJoinPoint.getSignature());
	}

}
