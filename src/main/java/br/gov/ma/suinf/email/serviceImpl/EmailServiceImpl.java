package br.gov.ma.suinf.email.serviceImpl;

import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.gov.ma.suinf.email.dto.EmailRequestDTO;
import br.gov.ma.suinf.email.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{
	
	 @Override
	    public void enviarEmail(EmailRequestDTO dto) {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost(dto.getSmtpHost());
	        mailSender.setPort(dto.getSmtpPort());
	        mailSender.setUsername(dto.getSmtpUsername());
	        mailSender.setPassword(dto.getSmtpPassword());

	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");

	        try {
	            MimeMessage message = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

	            helper.setFrom(dto.getFrom());
	            helper.setTo(dto.getTo());
	            helper.setSubject(dto.getSubject());
	            helper.setText(dto.getBodyHtml(), true);

	            mailSender.send(message);
	        } catch (MessagingException e) {
	            throw new RuntimeException("Erro ao enviar e-mail: " + e.getMessage(), e);
	        }
	    }
	}


