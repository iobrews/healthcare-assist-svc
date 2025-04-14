package in.iobrews.healthcaremcp.utils;

import in.iobrews.healthcaremcp.model.PatientContext;

public class PromptBuilder {

    public static String buildPrompt(String question, PatientContext context){
        return String.format("""
                    You are a virtual assistant helping Dr. Smith with a patient consultation.

                    Patient Name: %s
                    Age: %d
                    allergies: %s
                    medical conditions: %s
                    last consultation notes: %s

                    conversation history: %s

                    Doctor's question: %s
                """,
                context.getPatientName(),
                context.getPatientAge(),
                context.getAllergies(),
                context.getMedicalConditions(),
                context.getLastConsultationNotes(),
                context.getConversationHistory(),
                question);
    }
}
