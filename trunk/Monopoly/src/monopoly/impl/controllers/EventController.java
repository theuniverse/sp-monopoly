package monopoly.impl.controllers;

import java.util.List;

import monopoly.core.beans.event.IEvent;
import monopoly.core.services.IEventService;
import monopoly.core.services.IUserService;
import monopoly.impl.controllers.response.BaseResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventController extends BaseController
{
	@Autowired
	private IUserService userSerivce;

	@Autowired
	private IEventService eventService;

	@RequestMapping(value = "/event/fetch")
	public String fetch(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model)
	{
		if (!userSerivce.login(username, password))
		{
			model.addAttribute("message", AUTH_FAIL);
			return ERROR_PAGE;
		}

		List<IEvent> events = eventService.fetch(username, password);
		if (events == null)
		{
			model.addAttribute("message", "Unexpected error");
			return ERROR_PAGE;
		}

		String str = "{";
		for (IEvent event : events)
			str += event.toString() + ";";
		if (str.length() >= 2)
			str = str.substring(0, str.length() - 1) + "}";
		else
			str = "{}";

		BaseResponse result = new BaseResponse();
		result.put("size", "" + events.size());
		result.put("events", str);
		model.addAttribute("result", result);
		return RESULT_PAGE;
	}

}
