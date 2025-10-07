package com.careplus.billingservice.api;

import com.careplus.billingservice.client.AppointmentClient;
import com.careplus.billingservice.model.Bill;
import com.careplus.billingservice.repo.BillRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/bills")
public class BillingController {
    private final BillRepository repo;
    private final AppointmentClient client;
    public BillingController(BillRepository repo, AppointmentClient client) { this.repo=repo; this.client=client; }
    @GetMapping public List<Bill> all() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Bill> byId(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping public ResponseEntity<?> create(@Valid @RequestBody Bill b) {
        if (!client.appointmentExists(b.getAppointmentId())) return ResponseEntity.badRequest().body("Appointment not found");
        return ResponseEntity.ok(repo.save(b));
    }
    @PostMapping("/{id}/pay") public ResponseEntity<?> pay(@PathVariable Long id) {
        return repo.findById(id).map(b -> { b.setStatus("PAID"); return ResponseEntity.ok(repo.save(b)); })
                .orElse(ResponseEntity.notFound().build()); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { return repo.delete(id)?ResponseEntity.noContent().build():ResponseEntity.notFound().build(); }
}
