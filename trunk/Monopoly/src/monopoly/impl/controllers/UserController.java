package monopoly.impl.controllers;

import monopoly.core.services.IUserService;
import monopoly.impl.controllers.response.BaseResponse;

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
		if (!userSerivce.register(username, password))
		{
			model.addAttribute("message", "User already exists");
			return ERROR_PAGE;
		}

		BaseResponse result = new BaseResponse();
		result.put("message", "Registration succeeds");
		model.addAttribute("result", result);
		return RESULT_PAGE;
	}

	@RequestMapping(value = "/user/login")
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model)
	{
		if (!userSerivce.login(username, password))
		{
			model.addAttribute("message", AUTH_FAIL);
			return ERROR_PAGE;
		}

		BaseResponse result = new BaseResponse();
		result.put("message", "Login succeeds");
		model.addAttribute("result", result);
		return RESULT_PAGE;
	}
}
