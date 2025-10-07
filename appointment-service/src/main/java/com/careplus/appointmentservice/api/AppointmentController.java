package com.careplus.appointmentservice.api;

import com.careplus.appointmentservice.client.ExternalClients;
import com.careplus.appointmentservice.model.Appointment;
import com.careplus.appointmentservice.repo.AppointmentRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentRepository repo;
    private final ExternalClients clients;
    public AppointmentController(AppointmentRepository repo, ExternalClients clients) { this.repo=repo; this.clients=clients; }
    @GetMapping public List<Appointment> all() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Appointment> byId(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping public ResponseEntity<?> create(@Valid @RequestBody Appointment a) {
        if (!clients.userExists(a.getUserId())) return ResponseEntity.badRequest().body("User not found");
        if (!clients.doctorExists(a.getDoctorId())) return ResponseEntity.badRequest().body("Doctor not found");
        return ResponseEntity.ok(repo.save(a));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { return repo.delete(id)?ResponseEntity.noContent().build():ResponseEntity.notFound().build(); }
}
