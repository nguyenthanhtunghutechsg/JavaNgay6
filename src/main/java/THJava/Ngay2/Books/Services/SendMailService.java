package THJava.Ngay2.Books.Services;

import java.io.UnsupportedEncodingException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import THJava.Ngay2.Books.Models.User;
@Service
public class SendMailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmailForgotPassword(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("javaS5@cntt.com", "Tung NT");
		helper.setTo(recipientEmail);

		String subject = "Link password";

		String content = "<p>Chao`,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + link
				+ "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		javaMailSender.send(message);
	}

	public void sendVerificationEmail(User user, String siteURL)
			throws MessagingException, UnsupportedEncodingException {
		String toAddress = user.getEmail();
		String fromAddress = "javaS5@cntt.com";
		String senderName = "HUTECH";
		String subject = "Please verify your registration";
		String content = "Dear [[name]],<br>" + "Please click the link below to verify your registration:<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" + "Thank you,<br>" + senderName;

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);

		content = content.replace("[[name]]", user.getUsername());
		String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

		content = content.replace("[[URL]]", verifyURL);

		helper.setText(content, true);

		javaMailSender.send(message);

	}
}
