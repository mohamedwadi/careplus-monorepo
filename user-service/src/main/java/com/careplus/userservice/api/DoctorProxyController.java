package com.careplus.userservice.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class DoctorProxyController {

    // نستخدم RestTemplate محليًا لتفادي أي مشاكل حقن
    private final RestTemplate http = new RestTemplate();

    // فحص سريع أن user-service شغال
    @GetMapping(value = "/health", produces = MediaType.TEXT_PLAIN_VALUE)
    public String health() {
        return "USER-SERVICE: OK";
    }

    // فحص الاتصال المباشر بـ doctor-service (بدون Gateway)
    @GetMapping(value = "/doctor-ping-direct", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> doctorPingDirect() {
        try {
            String body = http.getForObject("http://127.0.0.1:8082/doctors/ping", String.class);
            return ResponseEntity.ok(body);
        } catch (Exception ex) {
            return ResponseEntity
                    .status(502)
                    .body("DIRECT ERROR: " + ex.getClass().getSimpleName() + " - " + ex.getMessage());
        }
    }

    // Endpoint المطلوب: ينادي doctor-service ويرد JSON
    @GetMapping(value = "/{id}/doctor-ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> doctorPing(@PathVariable Long id) {
        // مبدئيًا اتصال مباشر لتأكيد السريان (يمكن لاحقًا تغييره للـ Gateway)
        final String url = "http://127.0.0.1:8082/doctors/ping";
        Map<String, Object> result = new HashMap<>();
        try {
            String body = http.getForObject(url, String.class);
            result.put("userId", id);
            result.put("doctorService", body);
            result.put("via", "direct");
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            result.put("userId", id);
            result.put("doctorService", "ERROR");
            result.put("via", "direct");
            result.put("reason", ex.getClass().getSimpleName());
            result.put("message", String.valueOf(ex.getMessage()));
            result.put("targetUrl", url);
            return ResponseEntity.status(502).body(result);
        }
    }
}
