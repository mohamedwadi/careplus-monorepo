package com.careplus.doctorservice.repo;

import com.careplus.doctorservice.model.Doctor;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class DoctorRepository {
    private final Map<Long, Doctor> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);
    public List<Doctor> findAll() { return new ArrayList<>(store.values()); }
    public Optional<Doctor> findById(Long id) { return Optional.ofNullable(store.get(id)); }
    public Doctor save(Doctor d) { if (d.getId()==null) d.setId(seq.incrementAndGet()); store.put(d.getId(), d); return d; }
    public boolean delete(Long id) { return store.remove(id) != null; }
}
