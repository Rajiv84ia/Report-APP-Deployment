package in.rajiv.contrroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.rajiv.dto.SearchRequest;
import in.rajiv.entity.CitizenPlan;
import in.rajiv.service.RequestService;
import in.rajiv.service.RequestServiceImpl;

@Controller
public class RequestController {

	@Autowired
	private RequestService service;

	@PostMapping("/search")
	public String SearchHandler(@ModelAttribute("search") SearchRequest request, Model model ) {
		
		init(model);
		System.out.println(request);
		List<CitizenPlan> list = service.Search(request);
		System.out.println(list);
		model.addAttribute("list", list);
		return "index";
	}
	
	@GetMapping("/")
	public String LoadForm(Model model) {
		
		model.addAttribute("search",new SearchRequest());
		init(model);
		
		return "index";
	}

	private void init(Model model) {
		model.addAttribute("plan", service.getPlanName());
		model.addAttribute("status", service.getPlanStatus());
	}
	

}
