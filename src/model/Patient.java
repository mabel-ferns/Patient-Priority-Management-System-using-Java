package model;


import java.time.LocalDateTime;

public class Patient {

    private int patientId;
    private String name;
    private int age;
    private String gender;
    private String symptoms;
    private VitalSigns vitalSigns;

    private TriageLevel triageLevel;
    private LocalDateTime arrivalTime;

    public Patient(int patientId,
                   String name,
                   int age,
                   String gender,
                   String symptoms,
                   VitalSigns vitalSigns) {

        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.symptoms = symptoms;
        this.vitalSigns = vitalSigns;
        this.arrivalTime = LocalDateTime.now();
    }

    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public VitalSigns getVitalSigns() {
        return vitalSigns;
    }

    public TriageLevel getTriageLevel() {
        return triageLevel;
    }

    public void setTriageLevel(TriageLevel triageLevel) {
        this.triageLevel = triageLevel;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public String toString() {

        return "\nPatient ID: " + patientId +
               "\nName: " + name +
               "\nAge: " + age +
               "\nGender: " + gender +
               "\nSymptoms: " + symptoms +
               "\nVital Signs: " + vitalSigns +
               "\nTriage Level: " +
               (triageLevel != null
                    ? triageLevel.getLevel() + " - "
                      + triageLevel.getDescription()
                    : "Not Assigned") +
               "\nArrival Time: " + arrivalTime;
    }
}