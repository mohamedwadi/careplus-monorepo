package com.careplus.userservice.repo;

import com.careplus.userservice.model.User;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private final Map<Long, User> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);
    public List<User> findAll() { return new ArrayList<>(store.values()); }
    public Optional<User> findById(Long id) { return Optional.ofNullable(store.get(id)); }
    public User save(User u) { if (u.getId()==null) u.setId(seq.incrementAndGet()); store.put(u.getId(), u); return u; }
    public boolean delete(Long id) { return store.remove(id) != null; }
}
