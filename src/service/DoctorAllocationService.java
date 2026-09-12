package service;

import java.util.ArrayList;
import model.Doctor;

public class DoctorAllocationService {

    public Doctor findAvailableDoctor(ArrayList<Doctor> doctors) {

        for (Doctor doctor : doctors) {

            if (doctor.isAvailable()) {
                doctor.setAvailable(false);
                return doctor;
            }
        }

        return null;
    }
}