package com.scc.dao;

import com.scc.model.PatientAppointment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScenarioDao {

    public boolean addRecord(PatientAppointment appointment) {
        String sql = "INSERT INTO appointments (patient_name, doctor_name, consult_fee, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, appointment.getPatientName());
            pstmt.setString(2, appointment.getDoctorName());
            pstmt.setDouble(3, appointment.getConsultFee());
            pstmt.setString(4, appointment.getStatus());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting record: " + e.getMessage());
            return false;
        }
    }

    public List<PatientAppointment> getAllRecords() {
        List<PatientAppointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PatientAppointment appointment = new PatientAppointment(
                        rs.getInt("id"),
                        rs.getString("patient_name"),
                        rs.getString("doctor_name"),
                        rs.getDouble("consult_fee"),
                        rs.getString("status")
                );
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching records: " + e.getMessage());
        }
        return appointments;
    }
}