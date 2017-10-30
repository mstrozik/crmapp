package common.model.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailManager {

	private JavaMailSender mailSender;
	private MimeMessage mimeMessage;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void send() {
		if (mimeMessage != null && mailSender != null)
			this.mailSender.send(mimeMessage);
	}

	public void prepeareMessage(String from, String[] to, String subject, String content) {
		try {
			this.mimeMessage = prepeareMimeMessage(from, to, subject, content);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	private MimeMessage prepeareMimeMessage(String from, String[] to, String subject, String content)
			throws MessagingException {
		MimeMessage mime = this.mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mime, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(content, true);
		return mime;
	}

}
