package com.scc.ui;

import com.scc.dao.ScenarioDao;
import com.scc.model.PatientAppointment;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final ScenarioDao scenarioDao;
    private final Scanner scanner;

    public ConsoleUI(ScenarioDao scenarioDao, Scanner scanner) {
        this.scenarioDao = scenarioDao;
        this.scanner = scanner;
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n==================================================");
            System.out.println("          CLINIC PATIENT APPOINTMENT REGISTRY     ");
            System.out.println("==================================================");
            System.out.println("[1] Add New Appointment");
            System.out.println("[2] View Scheduled Appointments");
            System.out.println("[3] Exit System");
            System.out.println("==================================================");
            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> addAppointment();
                case 2 -> viewAppointments();
                case 3 -> {
                    running = false;
                    System.out.println("\nExiting system... Thank you!");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addAppointment() {
        System.out.println("\n--- BOOK APPOINTMENT ---");
        System.out.print("Enter Patient Name: ");
        String patientName = scanner.nextLine().trim();

        System.out.print("Enter Doctor Assigned: ");
        String doctorName = scanner.nextLine().trim();

        System.out.print("Enter Consultation Fee: ");
        double consultFee = readDouble();

        System.out.print("Enter Status (SCHEDULED / COMPLETED): ");
        String status = scanner.nextLine().trim().toUpperCase();

        if (patientName.isEmpty() || doctorName.isEmpty()) {
            System.out.println("Error: Patient name and doctor cannot be empty.");
            return;
        }

        PatientAppointment appointment = new PatientAppointment(patientName, doctorName, consultFee, status);
        if (scenarioDao.addRecord(appointment)) {
            System.out.println("\nSUCCESS: Appointment successfully booked!");
        } else {
            System.out.println("\nERROR: Failed to book appointment.");
        }
    }

    private void viewAppointments() {
        List<PatientAppointment> appointments = scenarioDao.getAllRecords();
        System.out.println("\n==================================================================================");
        System.out.println("                          CLINIC APPOINTMENT SCHEDULE                             ");
        System.out.println("==================================================================================");

        if (appointments.isEmpty()) {
            System.out.println("(No appointment records found)");
            System.out.println("==================================================================================");
            return;
        }

        String format = "+-----+----------------------+----------------------+-----------------+--------------+%n";
        String headerFormat = "| %-3s | %-20s | %-20s | %-15s | %-12s |%n";

        System.out.printf(format);
        System.out.printf(headerFormat, "ID", "PATIENT NAME", "DOCTOR", "CONSULT FEE", "STATUS");
        System.out.printf(format);

        for (PatientAppointment pa : appointments) {
            System.out.printf("| %-3d | %-20s | %-20s | %-15.2f | %-12s |%n",
                    pa.getId(), pa.getPatientName(), pa.getDoctorName(), pa.getConsultFee(), pa.getStatus());
        }
        System.out.printf(format);
        System.out.println("Total Records Displayed: " + appointments.size());
        System.out.println("==================================================================================");
    }

    private int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private double readDouble() {
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}