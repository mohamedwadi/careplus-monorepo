package com.careplus.doctorservice.api;

import com.careplus.doctorservice.model.Doctor;
import com.careplus.doctorservice.repo.DoctorRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorRepository repo;

    public DoctorController(DoctorRepository repo) {
        this.repo = repo;
    }

    // نقطة فحص سريعة
    @GetMapping("/ping")
    public String ping() {
        return "doctor-service: OK";
    }

    // جميع الأطباء — يدعم /doctors و /doctors/
    @GetMapping({"", "/"})
    public List<Doctor> all() {
        return repo.findAll();
    }

    // طبيب حسب المعرّف
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> byId(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // إنشاء طبيب
    @PostMapping
    public ResponseEntity<Doctor> create(@Valid @RequestBody Doctor d) {
        return ResponseEntity.ok(repo.save(d));
    }

    // تحديث طبيب
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(@PathVariable Long id, @Valid @RequestBody Doctor d) {
        return repo.findById(id)
                .map(ex -> {
                    ex.setName(d.getName());
                    ex.setSpecialty(d.getSpecialty());
                    return ResponseEntity.ok(repo.save(ex));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // حذف طبيب (حسب توقيع الريبو لديك)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return repo.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
