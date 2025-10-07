# CarePlus Monorepo (Spring Boot Microservices)

A simple healthcare demo app built with Spring Boot (Java 17) using a microservices architecture.

## Services
- `api-gateway` — routes API traffic to services
- `user-service` — manage patients/users
- `doctor-service` — manage doctors
- `appointment-service` — create/view appointments (links user + doctor)
- `billing-service` — create/view bills (links appointment)

## Quick Start (local)
Run each service with:
```
mvn spring-boot:run
```
Ports:
- gateway: 8080
- user: 8081
- doctor: 8082
- appointment: 8083
- billing: 8084

## Docker
From repo root:
```
docker compose up --build
```

## Test via Gateway
- `GET http://localhost:8080/users`
- `POST http://localhost:8080/users` with `{ "name":"Ali", "email":"ali@example.com" }`
- `POST http://localhost:8080/doctors` with `{ "name":"Dr. Sara", "specialty":"Cardiology" }`
- `POST http://localhost:8080/appointments` with `{ "userId":1, "doctorId":1, "datetime":"2025-10-10T09:00:00" }`
- `POST http://localhost:8080/bills` with `{ "appointmentId":1, "amount": 120.0 }`

## Notes
- In-memory storage for classroom/demo simplicity.
- Replace with a real DB later if needed.
