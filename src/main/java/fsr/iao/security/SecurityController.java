package fsr.iao.security;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fsr.iao.dao.RoleRepository;
import fsr.iao.dao.UserRepository;
import fsr.iao.entities.Role;
import fsr.iao.entities.User;

@Controller
public class SecurityController  {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
@GetMapping(path="/notAuthorized")
public String notAuthorizes(){
	return "notAuthorized";
}
@GetMapping(path="/login")
public String login(Principal principal){
 if (principal!=null && ((Authentication)principal).isAuthenticated()) {
	        return "forward:/produits";
	    } 
	return "login";
}
/*@GetMapping(path="/logout")
public String logout(){
	//SecurityContextHolder.clearContext();
	return "login";
}*/

@GetMapping(path="/formUser")
public String formUser(Model model){
	
	List<Role> roles=roleRepository.findAll();
	model.addAttribute("user", new User());
	model.addAttribute("roles", roles);	
	return "formUser";
}
@PostMapping(path="/addUser")
public String addUser(Model model,@Validated User user,BindingResult bindingResult 
		,@RequestParam(value="roleName") String roleName
		,@RequestParam(value="confirmPassword",required=true) String confirmPassword){
	
	List<Role> roles=roleRepository.findAll();
	model.addAttribute("roles", roles);	
	if(bindingResult.hasErrors())	
		{
		
		
		return "formUser";}
	
	if(!user.getPassword().equals(confirmPassword)){
		//bindingResult.rejectValue("confirmPassword", "password n'est pas le meme!");
		model.addAttribute("confirmPassword", "password n'est pas le meme!");
		
		return "formUser";
	}
	model.addAttribute("confirmPassword", "");

	Role r=roleRepository.findByRole(roleName);
	user.setActived(true);
	userRepository.save(user);
	user.getRoles().add(r);
	userRepository.save(user);
	model.addAttribute("user", user);
	return "redirect:/formUser";
}

}
