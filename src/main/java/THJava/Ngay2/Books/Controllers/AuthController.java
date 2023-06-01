package THJava.Ngay2.Books.Controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import THJava.Ngay2.Books.Exception.UserNotFoundException;
import THJava.Ngay2.Books.Models.CustomUserDetails;
import THJava.Ngay2.Books.Models.User;
import THJava.Ngay2.Books.Services.RoleService;
import THJava.Ngay2.Books.Services.UserService;
import THJava.Ngay2.Books.Utils.Utilities;
import net.bytebuddy.utility.RandomString;

@Controller
@ComponentScan("THJava.Ngay2.Books")
public class AuthController {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@GetMapping("/auth/me")
	public String findMe(Authentication authentication, Model model) {
		User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();

		if (user == null) {
			return "notfound";

		} else {
			model.addAttribute("roles", roleService.listAll());
			model.addAttribute("user", user);
			return "user/edit";
		}
	}

	@GetMapping("/login")
	public String Login() {
		return "auth/login";
	}

	@GetMapping("/forgot_password")
	public String showForgotPasswordForm() {
		return "auth/forgot_password_form";
	}

	@PostMapping("/forgot_password")
	public String processForgotPassword(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		String token = RandomString.make(30);

		try {
			userService.updateResetPasswordToken(token, email);
			String resetPasswordLink = Utilities.getSiteURL(request) + "/reset_password?token=" + token;
			sendEmail(email, resetPasswordLink);
			model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

		} catch (UserNotFoundException ex) {
			model.addAttribute("error", ex.getMessage());
		} catch (UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error", "Error while sending email");
		}

		return "auth/forgot_password_form";
	}

	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
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

	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
		User user = userService.getUserByTokenforgotpassWord(token);
		model.addAttribute("token", token);

		if (user == null) {
			model.addAttribute("message", "Invalid Token");
			return "message";
		}

		return "auth/reset_password_form";
	}

	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");

		User user = userService.getUserByTokenforgotpassWord(token);
		model.addAttribute("title", "Reset your password");

		if (user == null) {
			model.addAttribute("message", "Invalid Token");
			return "message";
		} else {
			userService.updatePassword(user, password);

			model.addAttribute("message", "You have successfully changed your password.");
		}

		return "auth/reset_password_form";
	}

}
