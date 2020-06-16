package fsr.iao.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fsr.iao.dao.RoleRepository;
import fsr.iao.dao.UserRepository;
import fsr.iao.entities.Role;
import fsr.iao.entities.User;

@RestController
public class ProduitRestController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@RequestMapping(path="/addUser2")
	public User addUser(Model model,@Valid User u,@RequestParam(value="roleName") String roleName,BindingResult bindingResult ){
		//if(bindingResult.hasErrors()) return "formUser";
		Role r=roleRepository.findByRole(roleName);
		userRepository.save(u);
		u.getRoles().add(r);
		userRepository.save(u);
		model.addAttribute("user", u);
		return u;
	}
}
