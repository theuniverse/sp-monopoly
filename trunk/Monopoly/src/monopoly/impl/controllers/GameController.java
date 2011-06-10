package monopoly.impl.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController extends BaseController
{

	@RequestMapping(value = "/game/create")
	public String start(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model)
	{
		return RESULT_PAGE;
	}

}
