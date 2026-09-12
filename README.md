# HealthSync

HealthSync is a Java project for managing patients in a hospital setting.

The basic idea is to make the process of registering patients and deciding who should be attended to first a little more organized. When a patient is registered, their basic details, symptoms and vital signs are entered. Based on the symptoms, the program gives the patient a priority level. Patients are then kept in a priority queue so that the higher-priority patients can be taken first.

The project also has a simple doctor allocation system which checks whether a doctor is available and assigns one to the patient.

## Features

- Register patients
- Enter patient symptoms and vital signs
- Give patients a priority level
- Maintain a priority queue
- Dispatch the next patient
- Assign an available doctor
- Check how many patients are waiting

## Technologies Used

- Java
- VS Code
- Git
- GitHub

Java concepts used in the project include classes and objects, constructors, encapsulation, enums, ArrayList, PriorityQueue, methods and lambda expressions.

## Project Structure

8u
HealthSync
└── src
    ├── model
    │   ├── Doctor.java
    │   ├── Patient.java
    │   ├── TriageLevel.java
    │   └── VitalSigns.java
    │
    ├── service
    │   ├── TriageService.java
    │   ├── PatientQueue.java
    │   └── DoctorAllocationService.java
    │
    └── Main.java
