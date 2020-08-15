package pers.rush.myblog.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/")
public class HomePageService {
	
	@RequestMapping(value = "/") 
	public ModelAndView page() {
		return new ModelAndView("base/homepage");
	}
	
	@RequestMapping(value = "/index")
	public ModelAndView getIndexPage(HttpServletResponse response) {
		return new ModelAndView("base/index");
	}
}
