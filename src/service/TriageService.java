package service;

import model.Patient;
import model.TriageLevel;

public class TriageService {

    public TriageLevel assessPatient(Patient patient) {

        String symptoms = patient.getSymptoms().toLowerCase();

        TriageLevel level;

        if (symptoms.contains("chest pain") ||
            symptoms.contains("unconscious") ||
            symptoms.contains("severe bleeding")) {

            level = TriageLevel.ESI_1;

        } else if (symptoms.contains("breathing problem") ||
                   symptoms.contains("high fever") ||
                   symptoms.contains("severe pain")) {

            level = TriageLevel.ESI_2;

        } else if (symptoms.contains("fever") ||
                   symptoms.contains("vomiting") ||
                   symptoms.contains("injury")) {

            level = TriageLevel.ESI_3;

        } else {
            level = TriageLevel.ESI_4;
        }

        patient.setTriageLevel(level);

        return level;
    }
}s