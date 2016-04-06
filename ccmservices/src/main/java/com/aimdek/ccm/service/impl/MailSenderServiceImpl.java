package com.aimdek.ccm.service.impl;

import static com.aimdek.ccm.util.CCMConstant.*;
import static com.aimdek.ccm.util.CCMConstant.EMAIL_TEMPLATE_PATH;
import static com.aimdek.ccm.util.CCMConstant.TEXT_HTML;
import static com.aimdek.ccm.util.CCMConstant.UTF8;

import java.util.HashMap;
import java.util.Map;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.aimdek.ccm.document.User;
import com.aimdek.ccm.service.MailSenderService;
import com.aimdek.ccm.util.CommonUtil;
/**
 * The Class MailSenderServiceImpl.
 * @author aimdek.team
 */
@Service(value="emailService")
public class MailSenderServiceImpl implements MailSenderService {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(MailSenderServiceImpl.class);
	
	/** The Constant MAP_KEY_USER. */
	private static final String MAP_KEY_USER = "user";
	
	/** The Constant MAP_KEY_PASSWORD. */
	private static final String MAP_KEY_PASSWORD = "password";
	
	/** The mail sender. */
	@Autowired
	private JavaMailSender mailSender;

	/** The velocity engine. */
	@Autowired
	private VelocityEngine velocityEngine;
	
	/**
	 * Send customer registration email.
	 *
	 * @param user the user
	 * @param password the password
	 */
	public void sendCustomerRegistrationEmail(User user, String password) {
		try {
			Multipart multipart = new MimeMultipart();
			MimeMessage msg = mailSender.createMimeMessage();
			msg.setSubject(CUSTOMER_REGISTRATION_EMAIL_SUBJECT);
			msg.setSentDate(CommonUtil.toDay());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail(), FALSE));
			
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put(MAP_KEY_USER, user);
			model.put(MAP_KEY_PASSWORD, password);

			String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, EMAIL_TEMPLATE_PATH, UTF8, model);

			BodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(text, TEXT_HTML);
			multipart.addBodyPart(htmlPart);
			// Set the message content!
			msg.setContent(multipart);

			mailSender.send(msg);
		} catch (MailException e) {
			LOGGER.error(e);
		} catch (MessagingException e) {
			LOGGER.error(e);
		}
	}
}
