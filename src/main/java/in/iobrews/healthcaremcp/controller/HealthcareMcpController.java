package in.iobrews.healthcaremcp.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.iobrews.healthcaremcp.model.PatientContext;
import in.iobrews.healthcaremcp.service.OpenAIService;
import in.iobrews.healthcaremcp.utils.PromptBuilder;

@RestController
@RequestMapping("/api/v1/health-assist-mcp")
public class HealthcareMcpController {

    private final Map<String, PatientContext> contextStore = new ConcurrentHashMap<>();
    private final OpenAIService openAIService = new OpenAIService();
    
    @PostMapping("/updateContext")
    public ResponseEntity<String> updateContext(@RequestBody PatientContext context){
        contextStore.put(context.getPatientId(), context);
        return ResponseEntity.ok("Context updated successfully for patient "+context.getPatientId());
    }

    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestParam String patientId, @RequestParam String question){
        PatientContext context = contextStore.get(patientId);
        if(context == null)
            return ResponseEntity.status(404).body("No context found for patient "+patientId);
        
        String prompt = PromptBuilder.buildPrompt(question, context);
        String response = openAIService.getResponse(prompt);

        String history = context.getConversationHistory() == null? "" : context.getConversationHistory();
        String updatedHistory = history+"\n Doctor's Question: "+question+" \n Assistant's Response: "+response;
        context.setConversationHistory(updatedHistory);

        return ResponseEntity.ok(response);
                
    }


}
