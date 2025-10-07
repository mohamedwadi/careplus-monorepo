package com.careplus.appointmentservice.repo;

import com.careplus.appointmentservice.model.Appointment;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AppointmentRepository {
    private final Map<Long, Appointment> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);
    public List<Appointment> findAll() { return new ArrayList<>(store.values()); }
    public Optional<Appointment> findById(Long id) { return Optional.ofNullable(store.get(id)); }
    public Appointment save(Appointment a) { if (a.getId()==null) a.setId(seq.incrementAndGet()); store.put(a.getId(), a); return a; }
    public boolean delete(Long id) { return store.remove(id) != null; }
}
