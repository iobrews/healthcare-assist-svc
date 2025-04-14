package in.iobrews.healthcaremcp.model;

import lombok.Data;

@Data
public class PatientContext {

    private String patientId;
    private String patientName;
    private int patientAge;

    private String medicalConditions;
    private String allergies;
    private String currentMedications;
    private String lastConsultationNotes;

    private String conversationHistory;

}
