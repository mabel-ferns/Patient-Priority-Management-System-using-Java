import java.util.ArrayList;
import java.util.Scanner;

enum TriageLevel {
    CRITICAL("Critical", "Immediate medical attention required"),
    URGENT("Urgent", "Prompt medical attention required"),
    NON_URGENT("Non-urgent", "Routine medical attention required");

    private final String level;
    private final String description;

    TriageLevel(String level, String description) {
        this.level = level;
        this.description = description;
    }

    String getLevel() {
        return level;
    }

    String getDescription() {
        return description;
    }
}

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Doctor> doctors = new ArrayList<>();

    static TriageService triageService = new TriageService();
    static PatientQueue patientQueue = new PatientQueue();
    static DoctorAllocationService doctorService =
            new DoctorAllocationService();

    static int patientId = 1001;

    static class TriageService {
        TriageLevel assessPatient(Patient patient) {
            return patient.getTriageLevel();
        }
    }

    static class DoctorAllocationService {
        Doctor findAvailableDoctor(ArrayList<Doctor> doctors) {
            if (doctors.isEmpty()) {
                return null;
            }

            return doctors.get(0);
        }
    }

    static class Doctor {
        private final int id;
        private final String name;
        private final String specialization;

        Doctor(int id, String name, String specialization) {
            this.id = id;
            this.name = name;
            this.specialization = specialization;
        }

        int getId() {
            return id;
        }

        String getName() {
            return name;
        }

        String getSpecialization() {
            return specialization;
        }
    }

    static class VitalSigns {
        private final double bloodPressure;
        private final double spo2;
        private final double heartRate;
        private final double temperature;

        VitalSigns(double bloodPressure, double spo2, double heartRate,
                   double temperature) {
            this.bloodPressure = bloodPressure;
            this.spo2 = spo2;
            this.heartRate = heartRate;
            this.temperature = temperature;
        }
    }

    static class Patient {
        private final int patientId;
        private final String name;
        private final VitalSigns vitals;

        Patient(int patientId, String name, int age, String gender,
                String symptoms, VitalSigns vitals) {
            this.patientId = patientId;
            this.name = name;
            this.vitals = vitals;
        }

        int getPatientId() {
            return patientId;
        }

        String getName() {
            return name;
        }

        TriageLevel getTriageLevel() {
            if (vitals.spo2 < 90 || vitals.heartRate > 120
                    || vitals.heartRate < 50 || vitals.temperature >= 40) {
                return TriageLevel.CRITICAL;
            }
            if (vitals.spo2 < 94 || vitals.heartRate > 100
                    || vitals.temperature >= 38) {
                return TriageLevel.URGENT;
            }
            return TriageLevel.NON_URGENT;
        }
    }

    static class PatientQueue {
        private final ArrayList<Patient> patients = new ArrayList<>();

        void addPatient(Patient patient) {
            patients.add(patient);
        }

        boolean isEmpty() {
            return patients.isEmpty();
        }

        int getQueueSize() {
            return patients.size();
        }

        Patient getNextPatient() {
            if (patients.isEmpty()) {
                return null;
            }

            int nextIndex = 0;
            for (int i = 1; i < patients.size(); i++) {
                if (priority(patients.get(i).getTriageLevel())
                        < priority(patients.get(nextIndex).getTriageLevel())) {
                    nextIndex = i;
                }
            }
            return patients.remove(nextIndex);
        }

        private int priority(TriageLevel level) {
            switch (level) {
                case CRITICAL:
                    return 1;
                case URGENT:
                    return 2;
                default:
                    return 3;
            }
        }
    }

    public static void main(String[] args) {

        addDoctors();

        while (true) {

            System.out.println("\n===== HEALTHSYNC =====");
            System.out.println("1. Register Patient");
            System.out.println("2. View Queue Size");
            System.out.println("3. Dispatch Next Patient");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    registerPatient();
                    break;

                case 2:
                    System.out.println(
                        "Patients waiting: "
                        + patientQueue.getQueueSize()
                    );
                    break;

                case 3:
                    dispatchPatient();
                    break;

                case 4:
                    System.out.println(
                        "Thank you for using HealthSync!"
                    );
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void addDoctors() {

        doctors.add(
            new Doctor(101, "Dr. Sharma", "General Medicine")
        );

        doctors.add(
            new Doctor(102, "Dr. Patel", "Emergency Medicine")
        );

        doctors.add(
            new Doctor(103, "Dr. Khan", "General Medicine")
        );
    }

    static void registerPatient() {

        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter symptoms: ");
        String symptoms = scanner.nextLine();

        System.out.print("Enter blood pressure: ");
        double bp = scanner.nextDouble();

        System.out.print("Enter SpO2: ");
        double spo2 = scanner.nextDouble();

        System.out.print("Enter heart rate: ");
        double heartRate = scanner.nextDouble();

        System.out.print("Enter temperature: ");
        double temperature = scanner.nextDouble();
        scanner.nextLine();

        VitalSigns vitals =
                new VitalSigns(bp, spo2, heartRate, temperature);

        Patient patient = new Patient(
                patientId++,
                name,
                age,
                gender,
                symptoms,
                vitals
        );

        TriageLevel level =
                triageService.assessPatient(patient);

        patientQueue.addPatient(patient);

        System.out.println("\nPatient registered successfully!");
        System.out.println("Patient ID: " + patient.getPatientId());
        System.out.println(
                "Triage Level: "
                + level.getLevel()
                + " - "
                + level.getDescription()
        );
    }

    static void dispatchPatient() {

        if (patientQueue.isEmpty()) {
            System.out.println("No patients waiting.");
            return;
        }

        Patient patient = patientQueue.getNextPatient();

        Doctor doctor =
                doctorService.findAvailableDoctor(doctors);

        System.out.println("\n===== PATIENT DISPATCH =====");
        System.out.println("Patient: " + patient.getName());

        System.out.println(
                "Priority: "
                + patient.getTriageLevel().getDescription()
        );

        if (doctor != null) {

            System.out.println(
                    "Assigned Doctor: "
                    + doctor.getName()
            );

            System.out.println(
                    "Specialization: "
                    + doctor.getSpecialization()
            );

        } else {

            System.out.println("No doctor currently available.");
        }
    }
}