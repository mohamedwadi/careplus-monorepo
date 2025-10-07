package com.careplus.billingservice.repo;

import com.careplus.billingservice.model.Bill;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class BillRepository {
    private final Map<Long, Bill> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);
    public List<Bill> findAll() { return new ArrayList<>(store.values()); }
    public Optional<Bill> findById(Long id) { return Optional.ofNullable(store.get(id)); }
    public Bill save(Bill b) { if (b.getId()==null) b.setId(seq.incrementAndGet()); store.put(b.getId(), b); return b; }
    public boolean delete(Long id) { return store.remove(id) != null; }
}
