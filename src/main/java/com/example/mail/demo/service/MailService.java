//package com.example.mail.demo.service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.mail.MailException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import com.example.mail.demo.model.MailMessage;
//
//@Service
//@Component
//public class MailService {
//
//	/*
//	 * The Spring Framework provides an easy abstraction for sending email by using
//	 * the JavaMailSender interface, and Spring Boot provides auto-configuration for
//	 * it as well as a starter module.
//	 */
//	private JavaMailSender javaMailSender;
//
//	/**
//	 * 
//	 * @param javaMailSender
//	 */
//	@Autowired
//	public MailService(JavaMailSender javaMailSender) {
//		this.javaMailSender = javaMailSender;
//	}
//
//	/**
//	 * 
//	 * @param user
//	 * @throws MailException
//	 */
//
//	public void sendEmail(MailMessage m) throws MailException {
//
//		/*
//		 * This JavaMailSender Interface is used to send Mail in Spring Boot. This
//		 * JavaMailSender extends the MailSender Interface which contains send()
//		 * function. SimpleMailMessage Object is required because send() function uses
//		 * object of SimpleMailMessage as a Parameter
//		 */
//
//		SimpleMailMessage mail = new SimpleMailMessage();
//		
//		mail.setTo(m.getEmailAddress());
//		mail.setSubject(m.getSubject());
//		mail.setText(m.getBodyText());
//
//		/*
//		 * This send() contains an Object of SimpleMailMessage as an Parameter
//		 */
//		javaMailSender.send(mail);
//	}
//
//	/**
//	 * This function is used to send mail that contains a attachment.
//	 * 
//	 * @param user
//	 * @throws MailException
//	 * @throws MessagingException
//	 */
//	public void sendEmailWithAttachment(MailMessage m) throws MailException, MessagingException {
//
//		MimeMessage message = javaMailSender.createMimeMessage();
//
//		MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//		helper.setTo(m.getEmailAddress());
//		helper.setSubject(m.getSubject());
//		helper.setText(m.getBodyText());
//
//		
//		ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
//		helper.addAttachment(classPathResource.getFilename(), classPathResource);
//
//		javaMailSender.send(message);
//	}
//
//}
