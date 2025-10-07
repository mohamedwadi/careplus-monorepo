package com.careplus.appointmentservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ExternalClients {
    private final RestClient rest;
    private final String userBase;
    private final String doctorBase;
    public ExternalClients(@Value("${user.service.url:http://localhost:8081}") String userBase,
                           @Value("${doctor.service.url:http://localhost:8082}") String doctorBase) {
        this.rest = RestClient.create();
        this.userBase = userBase;
        this.doctorBase = doctorBase;
    }
    public boolean userExists(Long id) {
        try {
            ResponseEntity<String> r = rest.get().uri(userBase + "/users/{id}", id).retrieve().toEntity(String.class);
            return r.getStatusCode().is2xxSuccessful();
        } catch (Exception e) { return false; }
    }
    public boolean doctorExists(Long id) {
        try {
            ResponseEntity<String> r = rest.get().uri(doctorBase + "/doctors/{id}", id).retrieve().toEntity(String.class);
            return r.getStatusCode().is2xxSuccessful();
        } catch (Exception e) { return false; }
    }
}
