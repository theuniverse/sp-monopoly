package monopoly.impl.controllers;

import java.util.ArrayList;
import java.util.List;

import monopoly.core.beans.IGame;
import monopoly.core.beans.IPlayer;
import monopoly.core.beans.field.IField;
import monopoly.core.services.IGameEventService;
import monopoly.core.services.IGameService;
import monopoly.impl.controllers.json.JsonInitInfo;
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
		
		List<IField> fields = new ArrayList<IField>();
		List<IPlayer> players = new ArrayList<IPlayer>();
		
		gameService.initInfo(username, players, fields);
		
		JsonInitInfo jii = new JsonInitInfo(game.getHost().getKey(), players, fields); 
		
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

	@RequestMapping(value = "/game/buyProperty")
	public String buyProperty(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("action") String action, Model model)
	{
		if (!checkUser(username, password, model))
			return ERROR_PAGE;

		if (!gameEventService.checkBuyProperty(username))
		{
			model.addAttribute("message",
					"You are not able to buy property right now");
			return ERROR_PAGE;
		}

		BaseResponse result = new BaseResponse();
		gameEventService.checkNextPlayer(username);
		if (action.equals("buy"))
		{
			if (!gameService.buyProperty(username))
			{
				model.addAttribute("message", "Not enough cash");
				return ERROR_PAGE;
			}
			else
			{
				result.put("message", "You bought a property");
				model.addAttribute("result", result);
				return RESULT_PAGE;
			}
		}
		else
		{
			result.put("message", "You did not buy a property");
			model.addAttribute("result", result);
			return RESULT_PAGE;
		}
	}

	@RequestMapping(value = "/game/save")
	public String save(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("amount") Long amount, Model model)
	{
		if (!checkUser(username, password, model))
			return ERROR_PAGE;

		if (!gameEventService.checkBankService(username))
		{
			model.addAttribute("message",
					"It's not time you save or withdraw money");
			return ERROR_PAGE;
		}

		BaseResponse result = new BaseResponse();
		gameEventService.checkNextPlayer(username);
		if (gameService.save(username, amount))
		{
			result.put("message", "You saved " + amount);
			model.addAttribute("result", result);
			return RESULT_PAGE;
		}
		else
		{
			model.addAttribute("message", "Not enough cash");
			return ERROR_PAGE;
		}
	}

	@RequestMapping(value = "/game/withdraw")
	public String withdraw(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("amount") Long amount, Model model)
	{
		if (!checkUser(username, password, model))
			return ERROR_PAGE;

		if (!gameEventService.checkBankService(username))
		{
			model.addAttribute("message",
					"It's not time you save or withdraw money");
			return ERROR_PAGE;
		}

		BaseResponse result = new BaseResponse();
		gameEventService.checkNextPlayer(username);
		if (gameService.withdraw(username, amount))
		{
			result.put("message", "You withdrawed " + amount);
			model.addAttribute("result", result);
			return RESULT_PAGE;
		}
		else
		{
			model.addAttribute("message", "Not enough cash");
			return ERROR_PAGE;
		}

	}

}
