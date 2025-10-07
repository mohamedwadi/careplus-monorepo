package com.careplus.billingservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AppointmentClient {
    private final RestClient rest;
    private final String baseUrl;
    public AppointmentClient(@Value("${appointment.service.url:http://localhost:8083}") String baseUrl) {
        this.baseUrl = baseUrl; this.rest = RestClient.create();
    }
    public boolean appointmentExists(Long id) {
        try {
            ResponseEntity<String> r = rest.get().uri(baseUrl + "/appointments/{id}", id).retrieve().toEntity(String.class);
            return r.getStatusCode().is2xxSuccessful();
        } catch (Exception e) { return false; }
    }
}
