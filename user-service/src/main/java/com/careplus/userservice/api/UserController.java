package com.careplus.userservice.api;

import com.careplus.userservice.model.User;
import com.careplus.userservice.repo.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    // يدعم كلا المسارين: /users و /users/
    @GetMapping({"", "/"})
    public List<User> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> byId(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping({"", "/"})
    public ResponseEntity<User> create(@Valid @RequestBody User u) {
        return ResponseEntity.ok(repo.save(u));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User u) {
        return repo.findById(id)
                .map(ex -> {
                    ex.setName(u.getName());
                    ex.setEmail(u.getEmail());
                    return ResponseEntity.ok(repo.save(ex));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return repo.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
