package com.scc.model;

public class PatientAppointment {
    private int id;
    private String patientName;
    private String doctorName;
    private double consultFee;
    private String status;

    public PatientAppointment(int id, String patientName, String doctorName, double consultFee, String status) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.consultFee = consultFee;
        this.status = status;
    }

    public PatientAppointment(String patientName, String doctorName, double consultFee, String status) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.consultFee = consultFee;
        this.status = status;
    }

    public int getId() { return id; }
    public String getPatientName() { return patientName; }
    public String getDoctorName() { return doctorName; }
    public double getConsultFee() { return consultFee; }
    public String getStatus() { return status; }
}