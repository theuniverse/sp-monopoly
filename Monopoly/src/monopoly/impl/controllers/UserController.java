package monopoly.impl.controllers;

import monopoly.core.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
	public static final String RESULT_PAGE = "result";

	@Autowired
	private IUserService userSerivce;

	@RequestMapping(value = "/user/register")
	public String register(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model) {
		if (username == null || password == null) {
			model.addAttribute("message",
					"Username or Password is not specified.");
			return RESULT_PAGE;
		}

		boolean isSucceed = userSerivce.register(username, password);
		if (!isSucceed) {
			model.addAttribute("message", "Username already exists.");
			return RESULT_PAGE;
		}

		model.addAttribute("username", username);
		model.addAttribute("password", password);
		return RESULT_PAGE;
	}

	@RequestMapping(value = "/user/login")
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model) {
		if (username == null || password == null) {
			model.addAttribute("message", "Username or Password is empty.");
			return RESULT_PAGE;
		}

		boolean isSucceed = userSerivce.login(username, password);
		if (!isSucceed) {
			model.addAttribute("message", "Authentication failed.");
			return RESULT_PAGE;
		}

		model.addAttribute("username", username);
		model.addAttribute("password", password);
		return RESULT_PAGE;
	}
}
