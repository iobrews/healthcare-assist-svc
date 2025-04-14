package in.iobrews.healthcaremcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class HealthcareMcpApplication {

  @Value("${NAME:World}")
  String name;

  @RestController
  class HelloworldController {
    @GetMapping("/")
    String hello() {
      return "Hello " + name + "!";
    }
  }

  public static void main(String[] args) {
    SpringApplication.run(HealthcareMcpApplication.class, args);
  }

}





/*
 /usr/bin/env /nix/store/8yqdr7xk055fgqjzhcjdspnf24w70qwp-zulu-ca-jdk-17.0.8.1/bin/java @/tmp/cp_4q3yykvhgqqosxso28jkfi2e6.argfile in.iobrews.healthcaremcp.HealthcareMcpApplication 

 curl --location --request POST 'http://localhost:8080/api/v1/health-assist-mcp/updateContext' \
--header 'Content-Type: application/json' \
--data-raw '{
"patientId": "123",
"name": "John Dale",
"age": 45,
"medicalConditions": "diabetes, hypertension",
"allergies": "penicillin",
"currentMedications": "metformin",
"lastConsultationNotes": "Patient advised to reduce sugar intake"
}'



 */