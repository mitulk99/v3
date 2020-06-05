package com.amazon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.amazon.util.Back;
import com.amazon.util.StoresDetails;
import com.amazon.util.UserRequirements; 

@Controller 
public class HelloController {
	@Autowired
	private Back be;
	@Autowired
	private UserRequirements UR;
	@RequestMapping("/hello")
	public String display(Model m) throws Exception
	{
		m.addAttribute("user", UR);
		return "hellopage";
	}
	@RequestMapping("/view")
	public String submit(@ModelAttribute UserRequirements user,Model m) throws Exception
	{
		
		List<StoresDetails> details=be.Backend(UserRequirements.builder()
												.pincode(user.getPincode())
												.radius(user.getRadius())
												.category(user.getCategory()).build());
		if(details.size()==0)
		{
			return "nostores";
		}
		m.addAttribute("details", details);
		return "viewpage";
	}
}
