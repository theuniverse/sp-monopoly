package monopoly.impl.controllers;

import monopoly.core.beans.IGame;
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
public class GameController extends BaseController
{
	@Autowired
	private IUserService userService;

	@Autowired
	private IGameService gameService;

	private boolean checkUser(String username, String password, Model model)
	{
		if (!gameService.checkUser(username, password))
		{
			model.addAttribute("message",
					"You have no access to any init-fetch-stage game");
			return false;
		}

		return true;
	}

	@RequestMapping(value = "/game/initFetch")
	public String initFetch(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model)
	{
		if (!checkUser(username, password, model))
			return ERROR_PAGE;

		IGame game = gameService.initFetch(username);

		BaseResponse result = new BaseResponse();
		result.put("Test", game.getPlayers().size());
		model.addAttribute("result", result);
		return RESULT_PAGE;
	}

}
