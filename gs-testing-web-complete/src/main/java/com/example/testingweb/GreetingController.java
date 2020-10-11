package com.example.testingweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class GreetingController {

	private final GreetingService service;

	public GreetingController(GreetingService service) {
		this.service = service;
	}

	@RequestMapping("/greeting")
	public @ResponseBody String greeting() {
		return service.greet();
	}

	@RequestMapping("/push")
	public @ResponseBody String push() {
		service.push("eQ2_hrk-ToWhfrcU0b3S6R:APA91bGW9jO754I8J2VDDtdTLFzNH39ABH9o_wvwJOGRPRYI4hQsosUuR6UdFxt3Y_iSpRpX4ILWpgd9j3RXUtg1BB5e8_zR4F1r53eIvvcY8lGgHcvUhKdvk8Pd8E6SXeWbNwWAgl0j");
		return service.push("eF4F9DowiUVbkJCcrJ1gDl:APA91bFUgDuk7LrUoDk-i_EfznPlamQORfFK1l4j2BCFygWabw2BUAJ4i3WqdGRbyiL_Dgt9NO03jUb829TMYZKMoAZh5Y78LElL_ooZZ5qgKHHL8Ngcxpf8CL94-PNia9tQ0wHGD88V");
	}
}