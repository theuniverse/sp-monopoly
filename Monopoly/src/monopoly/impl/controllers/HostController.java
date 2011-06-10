package monopoly.impl.controllers;

import monopoly.core.beans.IHost;
import monopoly.core.services.IGameService;
import monopoly.core.services.IUserService;
import monopoly.impl.controllers.response.BaseResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HostController extends BaseController
{
	@Autowired
	private IUserService userService;

	@Autowired
	private IGameService gameService;

	@RequestMapping(value = "/host/create")
	public String create(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model)
	{
		if (!userService.login(username, password))
		{
			model.addAttribute("message", AUTH_FAIL);
			return ERROR_PAGE;
		}

		IHost host = gameService.create(username);

		if (host == null)
		{
			model.addAttribute("message", "Failed to create host");
			return ERROR_PAGE;
		}

		BaseResponse result = new BaseResponse();
		result.put("host_id", host.getKey());
		model.addAttribute("result", result);
		return RESULT_PAGE;
	}

	@RequestMapping(value = "/host/join")
	public String join(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("hostid") Long hostid, Model model)
	{
		if (!userService.login(username, password))
		{
			model.addAttribute("message", AUTH_FAIL);
			return ERROR_PAGE;
		}

		String r = gameService.join(username, hostid);
		if (r != null)
		{
			model.addAttribute("message", r);
			return ERROR_PAGE;
		}

		BaseResponse result = new BaseResponse();
		result.put("message", "Join host succeeds");
		model.addAttribute("result", result);
		return RESULT_PAGE;
	}

	@RequestMapping(value = "/host/start")
	public String start(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("hostid") Long hostid, Model model)
	{
		if (!userService.login(username, password))
		{
			model.addAttribute("message", AUTH_FAIL);
			return ERROR_PAGE;
		}

		String r = gameService.start(username, hostid);
		if (r != null)
		{
			model.addAttribute("message", r);
			return ERROR_PAGE;
		}

		BaseResponse result = new BaseResponse();
		result.put("message", "Game begins now");
		model.addAttribute("result", result);
		return RESULT_PAGE;
	}

}
