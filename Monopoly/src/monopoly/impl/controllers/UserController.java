package monopoly.impl.controllers;

import monopoly.core.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController extends BaseController
{
	@Autowired
	private IUserService userSerivce;

	@RequestMapping(value = "/user/register")
	public String register(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model)
	{
		if (username == null || password == null)
		{
			model.addAttribute("message",
					"Username or Password is not specified.");
			return ERROR_PAGE;
		}

		boolean isSucceed = userSerivce.register(username, password);
		if (!isSucceed)
		{
			model.addAttribute("message", "User already exists");
			return ERROR_PAGE;
		}

		result.put("message", "Registration succeeds");
		model.addAttribute("result", result);
		return RESULT_PAGE;
	}

	@RequestMapping(value = "/user/login")
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model)
	{
		if (username == null || password == null)
		{
			model.addAttribute("message", "Username or Password is empty.");
			return ERROR_PAGE;
		}

		boolean isSucceed = userSerivce.login(username, password);
		if (!isSucceed)
		{
			model.addAttribute("message", "Authentication failed.");
			return ERROR_PAGE;
		}

		result.put("message", "Login succeeds");
		model.addAttribute("result", result);
		return RESULT_PAGE;
	}
}
