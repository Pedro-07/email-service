package br.gov.ma.suinf.email.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.ma.suinf.email.dto.EmailRequestDTO;
import br.gov.ma.suinf.email.service.EmailService;
import br.gov.ma.suinf.email.utils.RsaUtil;


@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private RsaUtil rsaUtil;

    @Autowired
    private EmailService emailService;

    @Value("${api.valid-key}")
    private String validKey;

    @PostMapping("/enviar")
    public ResponseEntity<?> sendEmail(@RequestBody Map<String, String> body,
                                       @RequestHeader("X-API-KEY") String apiKey) {
        try {
            if (!validKey.equals(apiKey)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Chave inválida");
            }

            String encryptedPayload = body.get("encryptedPayload");
            if (encryptedPayload == null) {
                return ResponseEntity.badRequest().body("Campo 'encryptedPayload' ausente");
            }

            String decryptedJson = rsaUtil.decrypt(encryptedPayload);
            ObjectMapper mapper = new ObjectMapper();
            EmailRequestDTO email = mapper.readValue(decryptedJson, EmailRequestDTO.class);

            emailService.enviarEmail(email);
            return ResponseEntity.ok("Email enviado com sucesso");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao processar: " + e.getMessage());
        }
    }
}

/*
 * @RestController
 * 
 * @RequestMapping("/api/email") public class EmailController {
 * 
 * 
 * private final EmailService emailService;
 * 
 * // Valor da chave esperada vem do application.yml
 * 
 * @Value("${emailservice.api-key}") private String expectedApiKey;
 * 
 * public EmailController(EmailService emailService) { this.emailService =
 * emailService; }
 * 
 * @PostMapping("/enviar") public ResponseEntity<String> enviar(@RequestBody
 * EmailRequestDTO dto,
 * 
 * @RequestHeader(value = "X-API-KEY", required = true) String apiKey) { if
 * (!expectedApiKey.equals(apiKey)) {
 * 
 * System.out.print("passou aqui");
 * 
 * return
 * ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Chave de API inválida."
 * ); }
 * 
 * try { emailService.enviarEmail(dto); return
 * ResponseEntity.ok("E-mail enviado com sucesso."); } catch (Exception e) {
 * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
 * .body("Erro ao enviar e-mail: " + e.getMessage()); } } }
 * 
 */