package com.spring.microserver.eureka;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Janelle Baetiong (300966120) and Sadia Rashid (300963357)
 * COMP303 - 001 - Lab Assignment#4
 */


@Controller
public class IndexController {

	@RequestMapping("/home")
	public String home() {
		return "index";
	}
}