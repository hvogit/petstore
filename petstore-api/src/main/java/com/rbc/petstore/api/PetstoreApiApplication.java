package com.rbc.petstore.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetstoreApiApplication {
	private final Log log = LogFactory.getLog(getClass());

	public static void main(String[] args) {
		SpringApplication.run(PetstoreApiApplication.class, args);
	}

}

// todo AngularJS app
// todo deploy to cloud: IBM BlueMix, AWS
// todo Authentication
// todo Authorization
// todo upload Photos https://github.com/cloud-native-java/rest/blob/master/rest-basics/src/main/java/demo/CustomerProfilePhotoRestController.java
// todo unit test (Mockito)
// todo Angular 5


// todo short GUID
// todo review spring-data, JPA2 (@Query, Criteria, stored procedure)
// todo review SQL, advanced features (window function)

