package THJava.Ngay2.Books.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("")
	public String Home() {
		return "home/index";
	}
}
