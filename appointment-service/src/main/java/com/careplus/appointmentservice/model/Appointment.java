package com.careplus.appointmentservice.model;

import jakarta.validation.constraints.NotNull;

public class Appointment {
    private Long id;
    @NotNull private Long userId;
    @NotNull private Long doctorId;
    @NotNull private String datetime;
    public Appointment() {}
    public Appointment(Long id, Long userId, Long doctorId, String datetime) { this.id=id; this.userId=userId; this.doctorId=doctorId; this.datetime=datetime; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }
    public String getDatetime() { return datetime; }
    public void setDatetime(String datetime) { this.datetime = datetime; }
}
