package customloginapplication;

import customloginapplication.dto.UserDto;
import customloginapplication.model.Product;
import customloginapplication.model.User;
import customloginapplication.service.ProductService;
import customloginapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private UserService userService;

	@Autowired
	private ProductService productService;
	public UserController(UserService userService) {
	
		this.userService = userService;
	}


	@GetMapping("/home")
	public String home(Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userdetail" , userDetails);
		return "home";
	}
	@GetMapping("/products")
	public List<Product> products() {
		List<Product> products = productService.getAllProducts();
		//model.addAttribute("Products" , products);
		return products;
	}
	
	@GetMapping("/login")
	public String login(Model model, UserDto userDto) {
		model.addAttribute("user", userDto);
		return "login";
	}
	
	
	@GetMapping("/register")
	public String register(Model model, UserDto userDto) {
		
		model.addAttribute("user", userDto);
		return "register";
	}
	
	@PostMapping("/register")
	public String registerSave(@ModelAttribute("user") UserDto userDto, Model model) {
		User user = userService.findByUsername(userDto.getUsername());
		if (user != null) {
			model.addAttribute("userexist", user);
			return "register";
			
		}
		userService.save(userDto);
		return "redirect:/register?success";
	}

}
