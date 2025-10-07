package com.careplus.billingservice.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Bill {
    private Long id;
    @NotNull private Long appointmentId;
    @Min(0) private double amount;
    private String status = "PENDING";
    public Bill() {}
    public Bill(Long id, Long appointmentId, double amount) { this.id=id; this.appointmentId=appointmentId; this.amount=amount; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
