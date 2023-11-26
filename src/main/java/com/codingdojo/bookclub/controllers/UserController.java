package com.codingdojo.bookclub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.bookclub.models.User;
import com.codingdojo.bookclub.services.BookService;
import com.codingdojo.bookclub.services.UserService;
import com.codingdojo.bookclub.validators.LoginValidator;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService uService;
	@Autowired
	private BookService bService;
	
// LOGIN AND REGISTRATION
	
// SHOW-------------------------------------------
	@GetMapping("")
	public String index(
			@ModelAttribute("newUser")User newUser,
			@ModelAttribute("loginUser") LoginValidator loginUser,
			Model viewModel) {
		// bind empty bean to reg and login form
//		viewModel.addAttribute("loginUser", new LoginValidator());
		return "index.jsp";
	}
// CREATE - REGISTER--------------------------------
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        
        // TO-DO Later -- call a register method in the service 
        // to do some extra validations and create a new user!
        User newestUser = this.uService.register(newUser, result);
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
            model.addAttribute("loginUser", new LoginValidator());
            return "index.jsp";
        }
        
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        session.setAttribute("userId", newestUser.getId());
        return "redirect:/users/home";
    }
// CREATE - LOGIN-----------------------------------
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginUser") LoginValidator newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
        // Add once service is implemented:
         User user = uService.login(newLogin, result);
    
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
    
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        
        session.setAttribute("userId", user.getId());
        return "redirect:/users/home";
    }
// SHOW - SUCCESSFUL LOGIN/REG-----------------------------
    @GetMapping("/home")
    public String homeRoute(Model viewModel, HttpSession session) {
//    	System.out.println(session.getAttribute("userId"));
    	if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
//    	System.out.println(session.getAttribute("userId"));
    	Long userId = (long)session.getAttribute("userId");
    	viewModel.addAttribute("currentUser", this.uService.findById(userId));
    	viewModel.addAttribute("allBooks", this.bService.getAll());
    	return "dashboard.jsp";
    }
// LOGOUT--------------------------------------------------
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
}
