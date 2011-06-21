package monopoly.impl.controllers;

import monopoly.core.beans.IGame;
import monopoly.core.services.IGameEventService;
import monopoly.core.services.IGameService;
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
	private IGameService gameService;

	@Autowired
	private IGameEventService gameEventService;

	private boolean checkUserAtInitStage(String username, String password,
			Model model)
	{
		if (!gameService.checkUserAtInitStage(username, password))
		{
			model.addAttribute("message",
					"You have no access to any init-fetch-stage game");
			return false;
		}

		return true;
	}

	private boolean checkUser(String username, String password, Model model)
	{
		if (!gameService.checkUser(username, password))
		{
			model.addAttribute("message",
					"You have no access to any started game");
			return false;
		}

		return true;
	}

	@RequestMapping(value = "/game/initFetch")
	public String initFetch(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model)
	{
		if (!checkUserAtInitStage(username, password, model))
			return ERROR_PAGE;

		IGame game = gameService.initFetch(username);

		BaseResponse result = new BaseResponse();
		result.put("Test", game.getPlayers().size());
		model.addAttribute("result", result);
		return RESULT_PAGE;
	}

	@RequestMapping(value = "/game/castDie")
	public String castDie(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model)
	{
		if (!checkUser(username, password, model))
			return ERROR_PAGE;

		if (!gameEventService.checkCastDie(username))
		{
			model.addAttribute("message", "It's not your turn");
			return ERROR_PAGE;
		}

		int step = gameService.castDie(username);

		gameEventService.checkNextPlayer(username);

		BaseResponse result = new BaseResponse();
		result.put("message", "You cast a die with value " + step);
		model.addAttribute("result", result);
		return RESULT_PAGE;
	}

}
