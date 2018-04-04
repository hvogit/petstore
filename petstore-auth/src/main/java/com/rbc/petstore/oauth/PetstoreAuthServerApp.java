package com.rbc.petstore.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PetstoreAuthServerApp {
    public static void main(String[] args) {
        SpringApplication.run(PetstoreAuthServerApp.class, args);
    }
}

/*
$ curl rbc:rbc@localhost:7070/oauth/token -d grant_type=password -d username=hvo -d password=hvo | jq .
ADMIN (hvo/hvo)
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MjIzMzgzNDksInVzZXJfbmFtZSI6Imh2byIsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXSwianRpIjoiMDI2ODRlZmUtYmYxMS00YmY0LTgxYjgtNjM0NTc0ZjBjYTU1IiwiY2xpZW50X2lkIjoicmJjIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.6FvdJTQpWTCroJdf6UVoDoc3T_D7cQQuDMaS2XxUp28",
    "token_type": "bearer",
    "expires_in": 3599,
    "scope": "read write",
    "jti": "02684efe-bf11-4bf4-81b8-634574f0ca55"
}

USER (ann/ann)
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MjIzMzg0NDMsInVzZXJfbmFtZSI6ImFubiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiJlYTBkNGQ0Ni0wNzQ3LTRmZDItYWQ5Mi1kMThlY2E0ZjYxYmIiLCJjbGllbnRfaWQiOiJyYmMiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.hLzZZiMwyikYV4CVxPXzxSKBP-PP-e7AhO3KyEJ1MeQ",
    "token_type": "bearer",
    "expires_in": 3599,
    "scope": "read write",
    "jti": "ea0d4d46-0747-4fd2-ad92-d18eca4f61bb"
}
 */